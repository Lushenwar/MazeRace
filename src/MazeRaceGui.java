import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.sound.sampled.*;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

@SuppressWarnings("serial")
public class MazeRaceGui extends JFrame implements ActionListener, KeyListener {

    // Constants for cell size and maze dimensions
    static final int CELL_SIZE = 25;
    static final int NUM_CELLS_WIDTH = 27;
    static final int NUM_CELLS_HEIGHT = 27;
    static final int NUM_COINS = 10;

    // Icons for maze elements and characters
    private final ImageIcon WALL = new ImageIcon("images/red square.png");
    private final ImageIcon OUT_OF_BOUNDS = new ImageIcon("images/black square.png");
    private final ImageIcon PATH = new ImageIcon("images/grey square.png");
    private final ImageIcon COIN = new ImageIcon("images/gold coin.gif");
    private final ImageIcon[] MARIO = { new ImageIcon("images/mario0.gif"), new ImageIcon("images/mario1.gif"),
            new ImageIcon("images/mario2.gif"), new ImageIcon("images/mario3.gif") };

    private final ImageIcon[] SONIC = { new ImageIcon("images/sonic0.gif"), new ImageIcon("images/sonic1.gif"),
            new ImageIcon("images/sonic2.gif"), new ImageIcon("images/sonic3.gif") };

    private ImageIcon[] currentCharacterSprites;

    // Player object
    private Player player;

    // Panels and maze structure
    private JPanel mazePanel = new JPanel();
    private Cell[][] maze = new Cell[NUM_CELLS_WIDTH][NUM_CELLS_HEIGHT]; // 2D array for the cells in the maze

    // Game state variables
    private int score = 100000;
    private int numCoinsCollected = 0;
    private double time = 0;

    // Static highscore variable
    private int highscore = MazeRaceOpeningScreen.highscore;

    // Scoreboard components
    private JPanel scoreboardPanel = new JPanel();
    private Timer gameTimer = new Timer(100, this);
    private JLabel scoreLabel = new JLabel("100000");
    private JLabel highscoreLabel = new JLabel(Integer.toString(highscore));
    private JLabel coinsCollectedLabel = new JLabel("0");
    private JLabel timerLabel = new JLabel("0");
    private JLabel scoreResultLabel = new JLabel("Score: ");
    private JLabel highscoreResultLabel = new JLabel("Highscore: ");
    private JLabel coinsCollectedResultLabel = new JLabel("Coins Collected: ");
    private JLabel timerResultLabel = new JLabel("Time: ");

    // Background music clip
    private Clip bgmClip;

    // Constructor Method
    public MazeRaceGui() {

        // Calls on methods to set up game components
        playBGM();
        selectCharacter();
        scoreboardPanelSetup();
        mazePanelSetup();
        frameSetup();

    }

    // Method to select the character sprites based on the chosen character
    private void selectCharacter() {
        if (MazeRaceOpeningScreen.avatarIcon == "2") {
            currentCharacterSprites = SONIC;
        } else {
            currentCharacterSprites = MARIO;
        }
        player = new Player(currentCharacterSprites[1]);
    }

    // Method to set up the scoreboard panel
    private void scoreboardPanelSetup() {

        // Set bounds and layout for the scoreboard panel
        scoreboardPanel.setBounds(0, 0, CELL_SIZE * NUM_CELLS_WIDTH, 50);
        scoreboardPanel.setLayout(null);

        // Set bounds for labels on the scoreboard panel
        scoreResultLabel.setBounds(scoreboardPanel.getWidth() - 150, 0, 100, 25);
        highscoreResultLabel.setBounds(scoreboardPanel.getWidth() - 150, scoreboardPanel.getHeight() / 2, 100, 25);
        coinsCollectedResultLabel.setBounds(10, 0, 100, 25);
        timerResultLabel.setBounds(10, scoreboardPanel.getHeight() / 2, 100, 25);
        scoreLabel.setBounds(scoreboardPanel.getWidth() - 50, 0, 100, 25);
        highscoreLabel.setBounds(scoreboardPanel.getWidth() - 50, scoreboardPanel.getHeight() / 2, 100, 25);
        coinsCollectedLabel.setBounds(110, 0, 100, 25);
        timerLabel.setBounds(60, scoreboardPanel.getHeight() / 2, 100, 25);

        // Add labels to the scoreboard panel
        scoreboardPanel.add(scoreResultLabel);
        scoreboardPanel.add(highscoreResultLabel);
        scoreboardPanel.add(coinsCollectedResultLabel);
        scoreboardPanel.add(timerResultLabel);
        scoreboardPanel.add(scoreLabel);
        scoreboardPanel.add(highscoreLabel);
        scoreboardPanel.add(coinsCollectedLabel);
        scoreboardPanel.add(timerLabel);

    }

    // Method to set up the maze panel
    private void mazePanelSetup() {

        // Set bounds and layout for the maze panel
        mazePanel.setBounds(0, 50, CELL_SIZE * NUM_CELLS_WIDTH, CELL_SIZE * NUM_CELLS_HEIGHT);
        mazePanel.setLayout(new GridLayout(NUM_CELLS_WIDTH, NUM_CELLS_HEIGHT));

        // Load the maze and place initial elements
        loadMaze();
        placeCoins();
        placePlayer();

    }

    // Method to place coins in the maze
    private void placeCoins() {

        // Finds empty cells to place coins in
        for (int coin = 1; coin <= NUM_COINS; coin++) {
            Cell cell = findEmptyCell();
            maze[cell.getRow()][cell.getCol()].setIcon(COIN); // Places coins in the maze
        }

    }

    // Method to find an empty cell in the maze
    private Cell findEmptyCell() {
        Cell cell = new Cell();
        do {
            cell.setRow((int) (Math.random() * 24) + 2);
            cell.setCol((int) (Math.random() * 24) + 2);
        } while (maze[cell.getRow()][cell.getCol()].getIcon() != PATH);
        return cell;
    }

    // Method to place the player in the maze
    private void placePlayer() {
        Cell cell = findEmptyCell();
        player.setRow(cell.getRow());
        player.setCol(cell.getCol());
        maze[cell.getRow()][cell.getCol()].setIcon(player.getIcon());
    }

    // Method to load the maze from a file
    private void loadMaze() {
        int row = 0; // Reads in 1 row at a time
        char[] line;
        try {
            Scanner inputFile = new Scanner(new File(MazeRaceDifficultySelectScreen.difficulty));
            while (inputFile.hasNext()) {
                line = inputFile.nextLine().toCharArray();
                for (int column = 0; column < line.length; column++)
                    fillCell(line[column], row, column);
                row++;
            }
            inputFile.close();
        } catch (FileNotFoundException error) {
            // Prints out an error message if the file is not found
            System.out.println(error);
        }
    }

    // Method to fill a cell in the maze based on the character read from the file
    private void fillCell(char character, int row, int column) {
        maze[row][column] = new Cell(row, column);
        if (character == 'W')
            maze[row][column].setIcon(WALL);
        else if (character == 'X')
            maze[row][column].setIcon(OUT_OF_BOUNDS);
        else if (character == '.')
            maze[row][column].setIcon(PATH);
        mazePanel.add(maze[row][column]);
    }

    // Method to set up the game frame
    private void frameSetup() {
        // Set title, size, and layout
        setTitle("Ryan's Maze Race");
        setSize(mazePanel.getWidth() + 15, mazePanel.getHeight() + 25 + scoreboardPanel.getHeight() + 10);
        setLayout(null);

        // Add the scoreboard and maze panel to the frame
        add(scoreboardPanel);
        add(mazePanel);

        // Add key listener
        addKeyListener(this);

        // Set default close operation and frame properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        // Start the game timer
        gameTimer.start();
    }

    // Method for handling timer actions
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gameTimer) {
            time += 0.1;
            time = Math.round(time * 10.0) / 10.0;
            timerLabel.setText(Double.toString(time));
            scoreLabel.setText(Integer.toString(score - (int) (time * 2000)));
        }
    }

    // Method for when a key is typed (not used)
    @Override
    public void keyTyped(KeyEvent event) {
        // NOT USED
    }

    // Method for when a key is pressed
    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP && maze[player.getRow() - 1][player.getCol()].getIcon() != WALL)
            movePlayer(-1, 0);
        player.setIcon(currentCharacterSprites[1]);
        if (key.getKeyCode() == KeyEvent.VK_RIGHT && maze[player.getRow()][player.getCol() + 1].getIcon() != WALL)
            movePlayer(0, 1);
        player.setIcon(currentCharacterSprites[2]);
        if (key.getKeyCode() == KeyEvent.VK_DOWN && maze[player.getRow() + 1][player.getCol()].getIcon() != WALL)
            movePlayer(1, 0);
        player.setIcon(currentCharacterSprites[3]);
        if (key.getKeyCode() == KeyEvent.VK_LEFT && maze[player.getRow()][player.getCol() - 1].getIcon() != WALL)
            movePlayer(0, -1);
        player.setIcon(currentCharacterSprites[0]);
    }

    // Method to move the player in the maze
    private void movePlayer(int dRow, int dColumn) {
        maze[player.getRow()][player.getCol()].setIcon(PATH);
        if (maze[player.getRow() + dRow][player.getCol() + dColumn].getIcon() == COIN) {
            numCoinsCollected++;
            coinsCollectedLabel.setText(Integer.toString(numCoinsCollected));
            playCoinSound(); // Play the coin sound effect
        }
        player.move(dRow, dColumn);
        maze[player.getRow()][player.getCol()].setIcon(player.getIcon());

        // Check if all coins are collected
        if (numCoinsCollected == NUM_COINS) {
            stopBGM(); // Stop the background music
            score -= (int) (2000 * time);
            gameTimer.stop();
            String playerName = JOptionPane.showInputDialog(this,
                    "<html>YOU WIN!<br>Your score is: " + score + "<br>With a time of: " + time + "s<br>Enter your name:</html>",
                    "Congratulations!", JOptionPane.PLAIN_MESSAGE);
            
            // If the score is in the top 5, prompt for name and save score
            if (LeaderboardFrame.isTopScore(score)) {
                LeaderboardFrame.addScore(playerName, score);
                LeaderboardFrame.saveScores();
            }

            int choice = JOptionPane.showOptionDialog(this, "Do you want to retry or return to the menu?", "Game Over",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Retry", "Menu" },
                    "Retry");

            if (choice == JOptionPane.YES_OPTION) {
                resetGame();
                playBGM(); // Restart the background music
            } else {
                new MazeRaceOpeningScreen();
                dispose();
            }
        }
    }

    // Method to play the background music
    private void playBGM() {
        try {
            File soundFile = new File("sounds/Bgm.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Get a Clip instance
            bgmClip = AudioSystem.getClip();

            // Open the audio clip and start playing
            bgmClip.open(audioIn);
            FloatControl volumeControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volumeControl.getValue() - 10.0f); // Reduce volume by 50%
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException event) {
            System.out.println("File Error: " + event.getMessage());
        }
    }

    // Method to stop the background music
    private void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
            bgmClip.close();
        }
    }

    // Method to play the coin sound effect
    private void playCoinSound() {
        try {
            File soundFile = new File("sounds/CoinSound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Get a Clip instance
            Clip clip = AudioSystem.getClip();

            // Open the audio clip and start playing
            clip.open(audioIn);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volumeControl.getValue() - 10.0f); // Reduce volume by 50%
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException event) {
            System.out.println("File Error: " + event.getMessage());
        }
    }

    // Method for when a key is released
    @Override
    public void keyReleased(KeyEvent event) {
        // No idle timer functionality
    }

    // Method to reset the game state for retry
    private void resetGame() {
        MazeRaceOpeningScreen.highscore = Math.max(score, highscore);
        setVisible(false);
        MazeRaceGui MazeRaceGui = new MazeRaceGui();
    }

    public static void main(String[] args) {
        new MazeRaceGui();
    }
}
