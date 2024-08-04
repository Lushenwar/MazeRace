// Name: Ryan Qi
// Date: May 26th 2024
// Course Code: ICS 3U1 Mr .Fernandez
// Title: SDP #2 Individual Project Video Game
//
//
// Description:
// A maze racing game with Mario or Sonic characters. Players move through a maze collecting coins while a timer counts down. The game ends when all coins are collected. It features a highscore system that saves the best score. Sound effects play when coins are collected. The GUI displays the maze, scoreboard, and menu options for navigation.
//
//
// Major Skills:
// 	File input/output (reading from and writing to files)
// 	Arrays
// 	Java swing: JFrame, JPanel, JMenuBar, JLabel, and JButton
// 	Actions
//	Objects and Classes
//	Constructors
//	Methods
//	GridLayout
//
//
// Added Features:
// 	Basic:
//		Get player image to Face the Proper Direction as they move
//		Add more Accurate Timing (ex. tenths of seconds)
//		Add a Menubar - with a number of options (New Game, Quit, etc.)
//		Add a separate Opening Screen before the game starts
//		Add a High Score label for the current game session
//		Add more Characters - user can select their character
//		Add Music and Sound Effects (ex. “Cha-ching” when you get a coin)
//		Add New Levels (with different mazes and settings)
//
//
//
//
//
//
//	Advanced:
// 		Add a Highscore with a player’s initials - save this information to an external text file; shows when the game is played and can get replaced by a new higher score
//		Add a Highscore Table - saves the Top 5 scores to an external text file;this is able to be viewed (scores are sorted) and the information will get updated if a new Top 5 high score is achieved
//
//
//
//
//
//
// Areas of Concern:
// Opening new frames using menubar occasionally opens a new separate window
//
//
//
//
// Learned how to use Stream class: https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
// Learned how to center text: https://www.tabnine.com/code/java/methods/javax.swing.JLabel/setHorizontalAlignment
//Learned how to create Menubar: https://stackoverflow.com/questions/46841444/creating-a-drop-down-menu-bar-in-java-gui 
//https://stackoverflow.com/questions/29061969/adding-menubar-to-jframe
//https://www.developer.com/java/java-menus/
//Learned how to change characters:
//https://stackoverflow.com/questions/46854093/switch-players-in-a-java-game
//Learned how to add audio:
//https://stackoverflow.com/questions/64509691/how-can-i-add-some-sound-to-my-java-jframe 

// This class serves as an entry to Maze Race game.
public class MazeRaceTest {

	// This method opens the MazeRaceOpeningScreen.
	public static void main(String[] args) {
		new MazeRaceOpeningScreen();
	}
}
