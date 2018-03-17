
import java.util.List;
import java.util.ArrayList;

// A BoundedGrid is a rectangular grid with a finite number of rows and columns.
public class BoundedGrid<E> {
	private Object[][] occupantArray; // the array storing the grid elements

	// Constructs an empty BoundedGrid with the given dimensions.
	// Precondition: 0 < rows and 0 < cols.
	public BoundedGrid(int rows, int cols) {
		occupantArray = new Object[rows][cols];
	}

	// Returns the number of rows.
	public int getNumRows() {
		return occupantArray.length;
	}

	// Returns the number of columns.
	public int getNumCols() {
		return occupantArray[0].length;
	}

	// Returns the object at location loc
	// (or null if the location is unoccupied).
	// Precondition: loc is valid in this grid.
	@SuppressWarnings("unchecked")
	public E get(Location loc) {
		return (E) occupantArray[loc.getRow()][loc.getCol()];
	}

	// Puts obj at location loc in this grid and returns the object previously
	// at that location (or null if the location is unoccupied).
	// Precondition: loc is valid in this grid.
	public E put(Location loc, E obj) {
		if (get(loc) != null) {
			E oldOccupant = get(loc);
			occupantArray[loc.getRow()][loc.getCol()] = obj;
			return oldOccupant;
		} else {
			occupantArray[loc.getRow()][loc.getCol()] = obj;
			return null;
		}
	}

	// Returns true if loc is valid in this grid, false otherwise.
	// Precondition: loc is not null.
	public boolean isValid(Location loc) {
		if (loc.getRow() >= 0 && loc.getCol() >= 0) {
			return ((loc.getRow() < occupantArray.length) && (loc.getCol() < occupantArray[0].length));
		} else {
			return false;
		}
	}

	// Removes the object at location loc from this grid and returns
	// the object that was removed (or null if the location is unoccupied).
	// Precondition: loc is valid in this grid.
	public E remove(Location loc) {
		Object temp = occupantArray[loc.getRow()][loc.getCol()];
		occupantArray[loc.getRow()][loc.getCol()] = null;
		return (E) temp;
	}

	// Returns a list of all occupied locations in this grid.
	public ArrayList<Location> getOccupiedLocations() {
		ArrayList<Location> locationList = new ArrayList<Location>();
		// hint: use the get method to determine if a location is empty
		for (int i = 0; i < occupantArray.length; i++) {
			for (int j = 0; j < occupantArray[0].length; j++) {
				Location loc = new Location(i, j);
				if (get(loc) != null) {
					locationList.add(loc);
				}
			}

		}
		return locationList;
	}
}
