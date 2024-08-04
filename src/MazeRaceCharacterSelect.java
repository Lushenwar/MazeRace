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

public class MazeRaceCharacterSelect extends JFrame implements ActionListener {

    // GUI elements
    private JLabel titleLabel = new JLabel("Character Selection");
    private static JPanel panel = new JPanel();
    private JLabel backgroundLabel;
    
    // Character icons
    private ImageIcon marioIcon = new ImageIcon("images/mario.png");
    private ImageIcon sonicIcon = new ImageIcon("images/sonic.png");
    
    // Background image
    private ImageIcon backgroundIcon = new ImageIcon("images/Background.jpg");
    
    // Labels for character icons
    private JLabel marioLabel = new JLabel(marioIcon);
    private JLabel sonicLabel = new JLabel(sonicIcon);
    
    // GUI buttons
    private JButton marioButton = new JButton("Select Mario");
    private JButton sonicButton = new JButton("Select Sonic");

    // Constructor
    public MazeRaceCharacterSelect() {
        panelSetup();
        frameSetup();
    }

    private void panelSetup() {
        // Setup the panel
        panel.setBounds(0, 0, 1920, 1080);
        panel.setLayout(null);
        
        // Setup the background label
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        backgroundLabel.setLayout(null);
        
        // Center the text of the JLabel
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        titleLabel.setBounds(0, 50, 1920, 100);
        
        // Setup buttons
        marioButton.setBackground(Color.RED);
        marioButton.setForeground(Color.WHITE);
        marioButton.setFont(new Font("SansSerif", Font.BOLD, 50));
        marioButton.setBounds(360, 950, 400, 75);
        marioButton.addActionListener(this);
        
        sonicButton.setBackground(Color.RED);
        sonicButton.setForeground(Color.WHITE);
        sonicButton.setFont(new Font("SansSerif", Font.BOLD, 50));
        sonicButton.setBounds(1160, 950, 400, 75);
        sonicButton.addActionListener(this);
        
        // Setup character icons
        marioLabel.setBounds(100, 125, 800, 800);
        sonicLabel.setBounds(1000, 150, 800, 800);
        
        // Add components to background label
        backgroundLabel.add(titleLabel);
        backgroundLabel.add(marioLabel);
        backgroundLabel.add(sonicLabel);
        backgroundLabel.add(marioButton);
        backgroundLabel.add(sonicButton);
        
        // Add the background label to the panel
        panel.add(backgroundLabel);
    }

    private void frameSetup() {
        // Setup the frame
        setTitle("Maze Race Character Selection");
        setSize(1920, 1080);
        setLayout(null);
        
        // Add the panel to the frame
        add(panel);
        
        // Configure the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // Handle action events
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == marioButton) {
            MazeRaceOpeningScreen.avatarIcon = "1";
            navigateToOpeningScreen();
        } else if (event.getSource() == sonicButton) {
            MazeRaceOpeningScreen.avatarIcon = "2";
            navigateToOpeningScreen();
        }
    }

    private void navigateToOpeningScreen() {
        setVisible(false);
        new MazeRaceOpeningScreen(); // Pass the selected character
    }
}
