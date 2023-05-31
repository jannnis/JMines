import java.util.Scanner;

public class Highscore {
    public static void showHighscores(){
        ScreenCleaner.clearConsole();
        ScoreHandler scoreHandler = new ScoreHandler();
        String highscoreList = scoreHandler.highscoreList(10);
        if(highscoreList.isEmpty()){
            System.out.println("There are no highscores yet :(");
        }
        else {
            System.out.println(highscoreList);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please press enter to return to the main Menu");
        scanner.nextLine();
        Menu.chooseOption();
    }
}
