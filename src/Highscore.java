import java.util.Scanner;

public class Highscore {

    // This method is used to display the high scores
    public static void showHighscores(){

        // Clearing the console screen
        ScreenCleaner.clearConsole();

        // Creating a new instance of ScoreHandler
        ScoreHandler scoreHandler = new ScoreHandler();

        // Get the high score list
        String highscoreList;
        int numberOfHighScores = 10;
        highscoreList = scoreHandler.highscoreList(numberOfHighScores);

        // Check if highscoreList is empty or not
        if(highscoreList == null || highscoreList.isEmpty())
        {
            // There are no high scores yet
            String noHighScoresMessage = "There are no highscores yet :(";
            System.out.println(noHighScoresMessage);
        }
        else
        {
            // Print the high score list
            System.out.println("Here are the high scores:");
            System.out.println(highscoreList);
        }

        // Initiate a new scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String returnPrompt = "Please press enter to return to the main Menu";
        System.out.println(returnPrompt);

            // Waiting for user input
            String userInput = scanner.nextLine();


        // Returning to the main menu
            Menu.chooseOption();
    }
}