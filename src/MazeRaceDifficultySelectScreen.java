import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MazeRaceDifficultySelectScreen extends JFrame implements ActionListener {

    // GUI elements
    private JButton difficulty1Button;
    private JButton difficulty2Button;
    private JPanel panel = new JPanel();

    // Background and fonts
    private ImageIcon background = new ImageIcon("images/Background.jpg");
    private JLabel backgroundLabel = new JLabel(background);
    private Font buttonFont = new Font("SansSerif", Font.BOLD, 50);
    private Font titleFont = new Font("SansSerif", Font.BOLD, 80);
    private Font descriptionFont = new Font("SansSerif", Font.PLAIN, 20);

    // Images for difficulties
    private ImageIcon difficulty1Image = new ImageIcon("images/difficulty1.jpg");
    private ImageIcon difficulty2Image = new ImageIcon("images/difficulty2.jpg");

    // Default difficulty file path
    static String difficulty = "data/maze.txt";

    // Constructor
    public MazeRaceDifficultySelectScreen() {
        frameSetup();
    }

    private void frameSetup() {
        // Setup the frame
        setTitle("Select Difficulty");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setResizable(false);

        // Setup panel
        panel.setLayout(null);
        panel.setBounds(0, 0, 1920, 1080);

        // Setup background
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        panel.add(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Difficulty Selection");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(600, 50, 800, 100);
        backgroundLabel.add(titleLabel);

        // Initialize buttons
        difficulty1Button = new JButton("Easy");
        difficulty2Button = new JButton("Hard");

        // Set button styles
        styleButton(difficulty1Button, 135, 850, 700, 100);
        styleButton(difficulty2Button, 1085, 850, 700, 100);

        // Add action listeners to buttons
        difficulty1Button.addActionListener(this);
        difficulty2Button.addActionListener(this);

        // Add images
        JLabel difficulty1Label = new JLabel(difficulty1Image);
        JLabel difficulty2Label = new JLabel(difficulty2Image);
        difficulty1Label.setBounds(185, 200, 600, 600);
        difficulty2Label.setBounds(1135, 200, 600, 600);

        // Description labels
        JLabel difficulty1Description = new JLabel("Maze is easy to navigate through", SwingConstants.CENTER);
        difficulty1Description.setFont(descriptionFont);
        difficulty1Description.setForeground(Color.WHITE);
        difficulty1Description.setBounds(135, 950, 700, 50);

        JLabel difficulty2Description = new JLabel("Do you like to overcome challenges? Navigate through a harder maze", SwingConstants.CENTER);
        difficulty2Description.setFont(descriptionFont);
        difficulty2Description.setForeground(Color.WHITE);
        difficulty2Description.setBounds(1085, 950, 700, 50);

        // Add components to the background label
        backgroundLabel.add(difficulty1Label);
        backgroundLabel.add(difficulty2Label);
        backgroundLabel.add(difficulty1Button);
        backgroundLabel.add(difficulty2Button);
        backgroundLabel.add(difficulty1Description);
        backgroundLabel.add(difficulty2Description);

        // Add panel to the frame
        add(panel);
        setVisible(true); // Make the frame visible
    }

    private void styleButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(buttonFont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the "Easy" button is clicked
        if (e.getSource() == difficulty1Button) {
            difficulty = "data/maze.txt"; // Set difficulty file path
            setVisible(false); // Hide the current frame
            new MazeRaceOpeningScreen(); // Show the opening screen
        } 
        // If the "Hard" button is clicked
        else if (e.getSource() == difficulty2Button) {
            difficulty = "data/maze1.txt"; // Set difficulty file path
            setVisible(false); // Hide the current frame
            new MazeRaceOpeningScreen(); // Show the opening screen
        }
        dispose(); // Close the difficulty selection screen after selecting a difficulty
    }
}
