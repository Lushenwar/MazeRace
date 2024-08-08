# MazeRace-Game

This video game has players navigate through a maze in the fastest time, collecting coins, and avoiding obstacles, with multiple classes managing the main menu, leaderboard, character selection, and difficulty selection.

## Languages/Tools
- Java

## Key Features
- Selection buttons that switch when user uses up and down arrows
- Scoreboard that updates in real-time
- Persistent leaderboard that saves the top scores to a file
- Background music that plays in the game and stops when transitioning to other frames

## Technical Breakdown
- Swing Components: Used for creating the graphical user interface, including JFrame, JPanel, JLabel, and JButton.
- Input/Output: Handled using standard Java file handling classes like FileReader and FileWriter.
- Timer Management: Implemented with javax.swing.Timer to schedule and manage timed events.
- Audio Handling: Managed using an audio library to play background music and sound effects.
- Highscore Algorithm: Reads scores from a file, sorts them in descending order, and stores them in a list. When a new score is added, the list is updated, re-sorted, and the top 5 scores are saved back to the file.
- Moving Algorithm: Manages character movement within a castle maze, handling animations and updating the character image based on movement direction and actions like jumping, moving, and stopping. It initializes the character's position, direction, and state, and updates these attributes based on user inputs and game events.

## Preview
![images1](https://github.com/user-attachments/assets/fc108f30-8ca3-4e20-859a-37942e6378dc)

![image2](https://github.com/user-attachments/assets/78a5853f-6fb8-4796-a562-a7fb863480ca)

![image3](https://github.com/user-attachments/assets/da75d579-761e-40a7-86ba-40df499d1e99)

![image4](https://github.com/user-attachments/assets/7a4d0ebb-e00c-4d6b-bb0a-5bf01a0b3ffc)

![image5](https://github.com/user-attachments/assets/2a6cefb9-454d-458f-a9b7-41648c18bfcb)


## Video Preview
https://github.com/user-attachments/assets/ff33434a-764e-4aae-9730-482b99bc2699

https://github.com/user-attachments/assets/d7fbeff6-da5f-4b69-88c7-f6583df308ca

