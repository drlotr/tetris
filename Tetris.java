public class Tetris implements ArrowListener
{
	private BoundedGrid<Block> grid;	// The grid containing the Tetris pieces.
	private BlockDisplay display;		// Displays the grid.
	private Tetrad activeTetrad;		// The active Tetrad (Tetris Piece).

	// Constructs a Tetris Game
	public Tetris()
	{
		grid = new BoundedGrid<Block>(20, 10);
		display = new BlockDisplay(grid);
		display.setArrowListener(this);
		display.setTitle("Tetris");
	}

	// Play the Tetris Game
	public void play()
	{
		activeTetrad = new Tetrad(grid);
		display.showBlocks();
		boolean forever = true;
		while(forever == true) {
			sleep(1);
			activeTetrad.translate(1, 0);
			display.showBlocks();
			if(activeTetrad.translate(1, 0) == false) {
				activeTetrad = new Tetrad(grid);
			}
			clearCompletedRows();
		}
	}


	// Precondition:  0 <= row < number of rows
	// Postcondition: Returns true if every cell in the given row
	//                is occupied; false otherwise.
	private boolean isCompletedRow(int row)
	{
		Location loc;
		boolean lol = true;
		for(int i = 0; i < grid.getNumCols(); i++) {
			loc = new Location(row, i);
			if(grid.get(loc) == null) {
				lol = false;
			}
		}
		return lol;
	}

	// Precondition:  0 <= row < number of rows;
	//                The given row is full of blocks.
	// Postcondition: Every block in the given row has been removed, and
	//                every block above row has been moved down one row.
	private void clearRow(int row)
	{
		Location loc = new Location(0, 0);
		for(int i = 0; i < grid.getNumCols(); i++) {
			loc = new Location(row, i);
			grid.get(loc).removeSelfFromGrid();
		}
		Location newLocation;
		for(int i = 0; i < loc.getCol(); i++) {
			for(int j = 0; j < loc.getRow(); j++) {
				loc = new Location(i, j);
				newLocation = new Location(loc.getRow() + 1, loc.getCol());
				grid.get(loc).moveTo(newLocation);
			}
		}
	}

	// Postcondition: All completed rows have been cleared.
	private void clearCompletedRows()
	{
		for(int i = grid.getNumRows() - 1; i >= 0; i--) {
			if(isCompletedRow(i)) {
				clearRow(i);
			}
		}
	}

	// Sleeps (suspends the active thread) for duration seconds.
	private void sleep(double duration)
	{
		final int MILLISECONDS_PER_SECOND = 1000;

		int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.err.println("Can't sleep!");
		}
	}


	// Creates and plays the Tetris game.
	public static void main(String[] args)
	{
		new Tetris().play();
	}

	@Override
	public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
		
	}

	@Override
	public void downPressed() {
		activeTetrad.translate(1, 0);
		display.showBlocks();
		
	}

	@Override
	public void leftPressed() {
		activeTetrad.translate(0, -1);
		display.showBlocks();
		
	}

	@Override
	public void rightPressed() {
		activeTetrad.translate(0, 1);
		display.showBlocks();
		
	}
}
