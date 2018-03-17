
import java.awt.Color;

public class Block
{
	private BoundedGrid<Block> grid;
	private Location location;
	private Color color;

	// Constructs a blue block, because blue is the greatest color ever!
	public Block()
	{
		color = null;
		grid = null;
		location = null;
	}

	// Gets the color of this block.
	public Color getColor()
	{
		return color;
	}

	// Sets the color of this block to newColor.
	public void setColor(Color newColor)
	{
		color = newColor;
	}

	// Gets the grid of this block,
	// or null if this block is not contained in a grid.
	public BoundedGrid<Block> getGrid()
	{
		return grid;
	}

	// Gets the location of this block,
	// or null if this block is not contained in a grid.
	public Location getLocation()
	{
		return location;
	}

	// Removes this block from its grid.
	// Precondition:  this block is contained in a grid.
	public void removeSelfFromGrid()
	{
		if(grid == null) {
			return;
		} else if(grid.get(location) != this) {
			return;
		} else {
			grid.remove(location);
			grid = null;
			location = null;
		}
	}

	// Puts this block into location loc of grid gr.
	// If there is another block at loc, it is removed.
	// Precondition:  (1) this block is not contained in a grid.
	//                (2) loc is valid in gr.
	public void putSelfInGrid(BoundedGrid<Block> gr, Location loc)
	{
		grid = gr;
		location = loc;
		Block oldBlock = grid.get(location);
		if(oldBlock != null) {
			// gr.remove(loc);
			// gr.put(loc, this);
			oldBlock.removeSelfFromGrid();
		}
		grid.put(location,this);
	}

	// Moves this block to newLocation.
	// If there is another block at newLocation, it is removed.
	// Precondition:  (1) this block is contained in a grid.
	//                (2) newLocation is valid in the grid of this block.
	public void moveTo(Location newLocation)
	{
		grid.remove(location);
		putSelfInGrid(grid, newLocation);
	}

	// Returns a string with the location and color of this block.
	public String toString()
	{
		return "Block[location=" + location + ",color=" + color + "]";
	}
}
