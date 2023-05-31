import java.util.Scanner;

public class Menu {

    public static void chooseOption() {

        // Clearing the console screen
        ScreenCleaner.clearConsole();

        // Creating a new Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Displaying welcome message
        String welcomeMessage = "Welcome to Minesweeper";
        System.out.println(welcomeMessage);
        System.out.println();
        System.out.println();
        System.out.println();

        // Displaying game options
        String optionsMessage = "Select one of the following Options:";
        System.out.println(optionsMessage);
        System.out.println();

        System.out.println("Enter S for a new small game (10x10 + 10 bombs) ");
        System.out.println("Enter M for a new medium-sized game (20x20 + 20 bombs) ");
        System.out.println("Enter L for a new large game (40x40 + 40 bombs) ");
        System.out.println("Enter C for a new custom game");
        System.out.println();
        System.out.println("Enter H for the highscores");
        System.out.println();
        System.out.println("Enter Q to quit the game");

        // Getting the user input
        String input;
        try {
            input = scanner.nextLine().toLowerCase();
        } catch (Exception e) {
            System.out.println("Error while reading input: " + e.getMessage());
            input = "";
        }

        // Processing the user input
        switch (input) {
            case "s":
                try {
                    int gameSizeSmall = 10;
                    int bombCountSmall = 10;
                    Game gSmall = new Game(gameSizeSmall, gameSizeSmall, bombCountSmall);
                    KeyListener.setGame(gSmall);
                    gSmall.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "m":
                try {
                    int gameSizeMedium = 20;
                    int bombCountMedium = 20;
                    Game gMedium = new Game(gameSizeMedium, gameSizeMedium, bombCountMedium);
                    KeyListener.setGame(gMedium);
                    gMedium.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "l":
                try {
                    int gameSizeLarge = 40;
                    int bombCountLarge = 40;
                    Game gLarge = new Game(gameSizeLarge, gameSizeLarge, bombCountLarge);
                    KeyListener.setGame(gLarge);
                    gLarge.start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "c":
                customGame();
                break;
            case "h":
                Highscore.showHighscores();
                break;
            case "q":
                System.exit(0);
                break;
            default:
                Menu.chooseOption();
        }
    }

    // Method to start a custom game
    public static void customGame() {

        // Clears the console screen
        ScreenCleaner.clearConsole();

        // Asks user for the game's height and saves it
        System.out.println("Please enter the height of the game");
        int height = saveIntInput();

        // Asks user for the game's width and saves it
        System.out.println("Please enter the width of the game");
        int width = saveIntInput();

        // Asks user for the number of bombs in the game and saves it
        System.out.println("Please enter the amount of bombs for the game");
        int bombs = saveIntInput();

        // Tries to create a new game with the given height, width, and bombs
        // If too many bombs were requested, an exception is thrown
        try {
            Game g = new Game(height, width, bombs);
            KeyListener.setGame(g);
            g.start();
        } catch (TooManyBombsException e) {
            System.out.println("Please select fewer Bombs");
            // Retry the process if too many bombs were chosen
            customGame();
        }
    }

    // Method to restart a game with the given height, width, and bombs
    public static void restartGame(int height,int width, int bombs){
        try {
            // Create and start a new game with the given parameters
            Game g = new Game(height, width, bombs);
            KeyListener.setGame(g);
            g.start();

            // Create and start another new game with the same parameters
            // This is a bit strange, perhaps an error or misunderstanding in the original code?
            g = new Game(height, width, bombs);
            KeyListener.setGame(g);
            g.start();

        } catch (TooManyBombsException e) {
            // Retry the process if too many bombs were chosen
            System.out.println("Please select fewer Bombs");
            customGame();
        }
    }

    // Method to get an integer input from the user
    private static int saveIntInput(){
        // Create a new scanner object to read from the console
        Scanner scanner = new Scanner(System.in);

        try {
            // Reads a line from the console
            String input = scanner.nextLine();
            // Tries to convert the input to an integer
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // If the input cannot be converted to an integer, prompts for a valid number and tries again
            System.out.println("Please enter a valid number");
            return saveIntInput();
        }
    }
}
