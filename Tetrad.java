import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

// Represents a Tetris piece.
public class Tetrad {
	private Block[] blocks; // The blocks for the piece.

	// Constructs a Tetrad.
	public Tetrad(BoundedGrid<Block> grid) {
		blocks = new Block[4];
		for(int i = 0; i < blocks.length; i++) {
			blocks[i] = new Block();
		}
		Color color = null;
		Location[] locs = new Location[4];
		int shape = 0;
		boolean next = false;
		Random r = new Random();
		shape = r.nextInt(7);
		if (shape == 0) {
			color = Color.RED;
			locs[0] = new Location(0, 5);
			locs[1] = new Location(0, 6);
			locs[2] = new Location(0, 3);
			locs[3] = new Location(0, 4);
			addToLocations(grid, locs);
		} else if (shape == 1) {
			color = Color.GRAY;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 3);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(0, 5);
			addToLocations(grid, locs);
		} else if (shape == 2) {
			color = Color.cyan;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 5);
			addToLocations(grid, locs);
		} else if (shape == 3) {
			color = Color.yellow;
			locs[0] = new Location(2, 4);
			locs[1] = new Location(1, 4);
			locs[2] = new Location(0, 4);
			locs[3] = new Location(2, 5);
			addToLocations(grid, locs);
		} else if (shape == 4) {
			color = Color.MAGENTA;
			locs[0] = new Location(2, 5);
			locs[1] = new Location(1, 5);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(2, 4);
			addToLocations(grid, locs);
		} else if (shape == 5) {
			color = Color.blue;
			locs[0] = new Location(1, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(1, 3);
			locs[3] = new Location(0, 4);
			addToLocations(grid, locs);
		} else if (shape == 6) {
			color = Color.green;
			locs[0] = new Location(1, 4);
			locs[1] = new Location(0, 4);
			locs[2] = new Location(0, 3);
			locs[3] = new Location(1, 5);
			addToLocations(grid, locs);
		}
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].setColor(color);
		}
	}

	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	// deltaCol columns to the right, if those positions are
	// valid and empty.
	// Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		BoundedGrid<Block> grid;
		grid = blocks[0].getGrid();
		Location[] oldLocs = removeBlocks();
		Location[] newLocs = new Location[blocks.length];
		for(int i = 0; i < newLocs.length; i++) {
			newLocs[i] = new Location(oldLocs[i].getRow() + deltaRow, oldLocs[i].getCol() + deltaCol);
		}
		if(areEmpty(grid, newLocs)) {
			addToLocations(grid, newLocs);
			return true;
		} else {
			addToLocations(grid, oldLocs);
			return false;
		}
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	// about its center, if the necessary positions are empty.
	// Returns true if successful and false otherwise.
	public boolean rotate()
	{
		BoundedGrid<Block> grid;
		grid = blocks[0].getGrid();

		Location[] oldLocs = removeBlocks();
		Location[] newLocs = new Location[blocks.length];
		
		int row = oldLocs[0].getRow();
		int col = oldLocs[0].getCol();
		for (int i = 0; i < newLocs.length; i++)
		{
			newLocs[i] = new Location(row - col + oldLocs[i].getCol(), row + col - oldLocs[i].getRow());
		}
		if (areEmpty(grid, newLocs))
		{
			addToLocations(grid, newLocs);
			return true;
		}
		else
		{
			addToLocations(grid, oldLocs);
			return false;
		}
	}

	// Precondition: The elements of blocks are not in any grid;
	// locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	// and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs) {
		for (int i = 0; i < locs.length; i++) {
			blocks[i].putSelfInGrid(grid, locs[i]);
		}
	}

	// Precondition: The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	// and their old locations returned.
	private Location[] removeBlocks() {
		Location[] locs = new Location[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			locs[i] = blocks[i].getLocation();
			blocks[i].removeSelfFromGrid();
		}
		return locs;
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	// and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs) {
		ArrayList<Location> occupied = grid.getOccupiedLocations();
		boolean empty = true;
		for (int i = 0; i < locs.length; i++) {
			for (Location loc : occupied) {
				if (loc.equals(locs[i]))
					empty = false;
			}
			if (!grid.isValid(locs[i]) || empty == false)
				return false;
		}
		return true;
	}
}
