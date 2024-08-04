import javax.swing.JLabel;

// Define cell class
public class Cell extends JLabel {

	// Create variables for row and columns
	private int row;
	private int col;

	// Constructor for when we don't know row and column
	public Cell() {

	}

	// Generate Constructor when we know row and column
	public Cell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	// Generate Getters and Setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	// Generate toString method
	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + "]";
	}

}
