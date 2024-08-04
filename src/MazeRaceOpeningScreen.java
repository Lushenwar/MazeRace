import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MazeRaceOpeningScreen extends JFrame implements ActionListener, KeyListener {

    // string value which determines which icon is selected
    // defaulted at character 1
    public static String avatarIcon = "1";
    public static int highscore = 0;

    // GUI elements
    private JLabel titleLabel = new JLabel("Maze Race");
    private static JPanel panel = new JPanel();
    private JButton[] buttons;
    private int currentSelection = 0;
    private final String arrow = "▶";
    
    // panel for the character
    private JPanel characterPanel = new JPanel();

    // Create new ImageIcon for background image
    private ImageIcon background = new ImageIcon("images/Background.jpg");

    private ImageIcon character;

    // Declare JLabel for storing the background image
    private JLabel lessonBackground = new JLabel(background);

    // GUI elements
    private JButton startButton = new JButton("Start");
    private JButton characterSelectionButton = new JButton("Character Selection");
    private JButton leaderboardButton = new JButton("Leaderboard");
    private JButton difficultySelectButton = new JButton("Select Difficulty");
    
    // declare border color variable outside of constructor to be accessed
    // throughout
    Border whiteline = BorderFactory.createLineBorder(Color.white, 4);

    // Constructor Method
    public MazeRaceOpeningScreen() {
        panelSetup();
        characterPanelSetup();
        frameSetup();
        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Initialize buttons array
        buttons = new JButton[] { startButton, characterSelectionButton, difficultySelectButton, leaderboardButton };
        buttons[currentSelection].setText(arrow + buttons[currentSelection].getText());
        buttons[currentSelection].setForeground(Color.RED);
        buttons[currentSelection].setBackground(Color.WHITE);
    }

    // Method to set up the panel
    private void panelSetup() {
        // Set bounds and layout for the panel
        panel.setBounds(0, 0, 1920, 1080);
        panel.setLayout(null);

        // Add the background label
        lessonBackground.setBounds(0, 0, 1920, 1080);
        panel.add(lessonBackground);

        // Set button background colors
        startButton.setBackground(Color.RED);
        characterSelectionButton.setBackground(Color.RED);
        leaderboardButton.setBackground(Color.RED);
        difficultySelectButton.setBackground(Color.RED);

        // Center the text of the JLabel
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Set foreground colors for labels and buttons
        titleLabel.setForeground(Color.RED);
        startButton.setForeground(Color.WHITE);
        characterSelectionButton.setForeground(Color.WHITE);
        leaderboardButton.setForeground(Color.WHITE);
        difficultySelectButton.setForeground(Color.WHITE);

        // Set font for title and buttons
        Font marioFont = new Font("SansSerif", Font.BOLD, 80);
        titleLabel.setFont(marioFont);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 50);
        startButton.setFont(buttonFont);
        characterSelectionButton.setFont(buttonFont);
        leaderboardButton.setFont(buttonFont);
        difficultySelectButton.setFont(buttonFont);

        // Set bounds for labels and buttons
        titleLabel.setBounds(50, 100, 600, 100);
        startButton.setBounds(50, 500, 700, 100); // Moved up
        characterSelectionButton.setBounds(50, 610, 700, 100); // Moved up
        difficultySelectButton.setBounds(50, 720, 700, 100); // Switched position
        leaderboardButton.setBounds(50, 830, 700, 100); // Switched position

        // Add action listeners for buttons
        startButton.addActionListener(this);
        characterSelectionButton.addActionListener(this);
        difficultySelectButton.addActionListener(this);
        leaderboardButton.addActionListener(this);

        // Add labels and buttons to the panel
        lessonBackground.setLayout(null);
        lessonBackground.add(titleLabel);
        lessonBackground.add(startButton);
        lessonBackground.add(characterSelectionButton);
        lessonBackground.add(leaderboardButton);
        lessonBackground.add(difficultySelectButton);
    }
    
    // Method to create the character panel
    private void characterPanelSetup() {
        // Determine the image to display for the character
        character = new ImageIcon("images/mariosonic.png");

        // Create and configure the character panel
        characterPanel.setBorder(whiteline);
        characterPanel.setBounds(1000, 130, 900, 800); // Adjusted size and position
        characterPanel.setLayout(null); // Use absolute positioning
        characterPanel.setVisible(true); // Make it visible

        // Create and add the JLabel image of the character to the panel
        JLabel characterLabel1 = new JLabel(character);
        characterLabel1.setBounds(50, 0, 800, 800);
        characterPanel.add(characterLabel1);

        // Add the character panel to the background label
        lessonBackground.add(characterPanel);
    }

    // Method to set up the frame
    private void frameSetup() {
        setTitle("Maze Race");
        setSize(panel.getWidth() + 15, panel.getHeight() + 25);
        setLayout(null);

        // Add the panel to the frame
        add(panel);

        // Configure the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // Handle button click events
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            setVisible(false);
            MazeRaceGui MazeRaceGui = new MazeRaceGui(); // Pass the selected character and an integer value
        } else if (event.getSource() == characterSelectionButton) {
            setVisible(false);
            MazeRaceCharacterSelect MazeRaceCharacterSelect = new MazeRaceCharacterSelect();
        } else if (event.getSource() == leaderboardButton) {
            setVisible(false);
            LeaderboardFrame LeaderboardFrame = new LeaderboardFrame();
        } else if (event.getSource() == difficultySelectButton) {
            setVisible(false);
            MazeRaceDifficultySelectScreen MazeRaceDifficultySelectScreen = new MazeRaceDifficultySelectScreen();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_DOWN:
                buttons[currentSelection].setText(buttons[currentSelection].getText().substring(arrow.length()));
                buttons[currentSelection].setForeground(Color.WHITE);
                buttons[currentSelection].setBackground(Color.RED);
                currentSelection = (currentSelection + 1) % buttons.length;
                buttons[currentSelection].setText(arrow + buttons[currentSelection].getText());
                buttons[currentSelection].setForeground(Color.RED);
                buttons[currentSelection].setBackground(Color.WHITE);
                break;
            case KeyEvent.VK_UP:
                buttons[currentSelection].setText(buttons[currentSelection].getText().substring(arrow.length()));
                buttons[currentSelection].setForeground(Color.WHITE);
                buttons[currentSelection].setBackground(Color.RED);
                currentSelection = (currentSelection - 1 + buttons.length) % buttons.length;
                buttons[currentSelection].setText(arrow + buttons[currentSelection].getText());
                buttons[currentSelection].setForeground(Color.RED);
                buttons[currentSelection].setBackground(Color.WHITE);
                break;
            case KeyEvent.VK_ENTER:
                buttons[currentSelection].doClick();
                currentSelection = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
