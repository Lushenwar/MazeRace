import javax.swing.ImageIcon;

public class Player extends Cell{
	
	//Constructor to set the image
	public Player(ImageIcon image){
		
		setIcon(image);
		
	}
	
	//Method to move the player by the row and columns
	public void move(int dRow, int dColumn){
		
		setRow(getRow()+dRow);
		setCol(getCol()+dColumn);
		
	}
	
}
