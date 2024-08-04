import javax.swing.*;
import javax.swing.border.Border;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class LeaderboardFrame extends JFrame implements ActionListener {

    // maximum entries for the leaderboard is 5
    private static final int MAX_ENTRIES = 5;

    // array to store all the user scores and usernames
    private static UserScore[] scores = new UserScore[MAX_ENTRIES];

    // JLabel arrays to score the usernames and scores
    private JLabel[] scoreLabels = new JLabel[5];
    private JLabel[] usernameLabels = new JLabel[5];

    // JPanel to store leaderboard content
    private JPanel leaderboardPanel = new JPanel();

    // JLabel to store background image
    private JLabel backgroundLabel = new JLabel(new ImageIcon("images/Background.jpg"));

    // JLabel to store the "Leaderboard title"
    private JLabel titleLabel = new JLabel();

    // JButton to navigate back home
    private JButton homeButton = new JButton();

    // declare border color variable outside of constructor to be accessed throughout
    Border whiteline = BorderFactory.createLineBorder(Color.white, 4);

    // constructor to set up leaderboard frame
    public LeaderboardFrame() {
        // call methods to set up frame
        leaderboardSetup(); // sets up leaderboard content
        frameSetup(); // sets up the frame
    }

    // method to set up leaderboard panel and its components
    private void leaderboardSetup() {
        // background set up
        // create and configure the background label containing the image
        backgroundLabel.setLayout(null);
        backgroundLabel.setBounds(0, 0, 1000, 600);
        add(backgroundLabel);

        // set up leaderboard panel
        leaderboardPanel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black background
        leaderboardPanel.setBorder(whiteline);
        leaderboardPanel.setBounds(200, 75, 600, 400); // Set size and position
        leaderboardPanel.setLayout(null); // Use absolute positioning
        leaderboardPanel.setVisible(true); // Make it visible
        backgroundLabel.add(leaderboardPanel);

        // set up title label
        titleLabel.setLayout(null);
        titleLabel.setText("LEADERBOARD"); // set the text
        titleLabel.setForeground(Color.WHITE); // set color of text
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 60)); // Set font and size
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 600, 75);
        leaderboardPanel.add(titleLabel); // add to panel

        // set up button
        homeButton.setBounds(225, 325, 150, 50);
        homeButton.addActionListener(this); // adds an action listener to listen if clicked
        homeButton.setText("HOME"); // set the text
        homeButton.setBackground(Color.RED);
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font("SansSerif", Font.BOLD, 25));
        leaderboardPanel.add(homeButton); // adds the button to panel

        // iterate through the top 5 to set up the top scorers and their scores
        for (int index = 0; index < MAX_ENTRIES; index++) {

            // create the labels
            usernameLabels[index] = new JLabel();
            scoreLabels[index] = new JLabel();

            if (scores[index] != null) { // if the array is not empty, set the values accordingly
                usernameLabels[index].setText(Integer.toString(index + 1) + ". " + scores[index].getUsername());
                scoreLabels[index].setText(String.valueOf(scores[index].getScore()));
            } else { // if the array is empty, set it as empty
                usernameLabels[index].setText(Integer.toString(index + 1) + ". " + "N/A");
                scoreLabels[index].setText("0");
            }

            // set bounds for the labels
            usernameLabels[index].setBounds(50, 85 + index * 50, 200, 30);
            scoreLabels[index].setBounds(300, 85 + index * 50, 100, 30);

            // set font for the labels
            usernameLabels[index].setFont(new Font("SansSerif", Font.PLAIN, 20));
            scoreLabels[index].setFont(new Font("SansSerif", Font.PLAIN, 20));

            // set the colors of the text
            usernameLabels[index].setForeground(Color.white);
            scoreLabels[index].setForeground(Color.white);

            // add labels to the panel
            leaderboardPanel.add(usernameLabels[index]);
            leaderboardPanel.add(scoreLabels[index]);
        }
    }

    // method to set up the frame and its properties
    private void frameSetup() {
        // sets title of the frame
        setTitle("Maze Race");

        // sets the size of the frame
        setSize(1000, 600);

        // sets the layout manager to null
        setLayout(null);

        // ensures the program closes on exit
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // fixes the frame size
        setResizable(false);
        setVisible(true);
    }

    // method to load the scores from the leaderboard.txt file
    public static void fillScores() {

        // use try and catch structure to read file
        try {
            // create scanner object to read file
            Scanner inputFile = new Scanner(new File("leaderboard.txt"));

            // delimiter to exclude certain arguments from scanner object
            inputFile.useDelimiter(",|\r\n|\n|\r");

            // read the file and fill the scores array
            int index = 0;
            while (inputFile.hasNext() && index < MAX_ENTRIES) {
                if (inputFile.hasNext()) { // checks if there it is not empty
                    String name = inputFile.next();

                    if (inputFile.hasNextInt()) { // checks if it is not empty
                        int score = inputFile.nextInt();
                        scores[index] = new UserScore(name, score); // assigns the value to array
                        index++;
                    }
                }
            }

            // close the scanner object
            inputFile.close(); // external file can now be used while this program is running

        } catch (FileNotFoundException e) { // catch if file error
            System.out.println("File error");
        }
    }

    // method to check if the score is bigger than the fifth score
    public static void addScore(String name, int score) {
        // create a new score object
        UserScore newScore = new UserScore(name, score);

        // iterate through each of the scores' arrays values
        for (int index = 0; index < MAX_ENTRIES; index++) {

            // if the scores in the array is empty
            // or the new score is higher than a score in the array
            if (scores[index] == null || scores[index].getScore() < score) {

                // shift the scores to make room for new score
                for (int count = MAX_ENTRIES - 1; count > index; count--) {
                    // shifts the scores one up
                    scores[count] = scores[count - 1];
                }

                // assigns the new position with new score
                scores[index] = newScore;
                break; // breaks the loop
            }
        }
    }

    // method to save the scores into leaderboard.txt file
    public static void saveScores() {

        // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
        // filters out null scores
        UserScore[] nonNullScores = Arrays.stream(scores).filter(score -> score != null).toArray(UserScore[]::new);

        // sorts the scores in descending order
        Arrays.sort(nonNullScores, Comparator.comparingInt(UserScore::getScore).reversed());

        // try catch structure to write on file
        try {

            // create printerwriter object to write on leaderboard file
            PrintWriter writer = new PrintWriter(new FileWriter("leaderboard.txt"));

            // write each score of the array by looping each index
            for (UserScore score : nonNullScores) {

                // converts the data into string to put into txt file
                String data = String.format("%s,%d", score.getUsername(), score.getScore());
                writer.println(data);
            }

            // close printwriter object
            writer.close();

        } catch (IOException e) { // handles IO exception
            e.printStackTrace();
        }
    }

    // method to check if the score is a top score;
    public static boolean isTopScore(int score) {
        return scores[MAX_ENTRIES - 1] == null || score > scores[MAX_ENTRIES - 1].getScore();
    }

    // method to check actions performed
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == homeButton) {

            // close the window
            setVisible(false);

            // open the menu window
            new MazeRaceOpeningScreen();
        }
    }

    // Dummy UserScore class to compile the LeaderboardFrame
    static class UserScore {
        private String username;
        private int score;

        public UserScore(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public String getUsername() {
            return username;
        }

        public int getScore() {
            return score;
        }
    }
}
