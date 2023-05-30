import java.util.Scanner;

public class Menu {
    public static void chooseOption(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Select one of the following Options:");
        System.out.println();
        System.out.println("Enter S for a new small game (10x10 + 10 bombs) ");
        System.out.println("Enter M for a new medium-sized game (20x20 + 20 bombs) ");
        System.out.println("Enter L for a new large game (40x40 + 40 bombs) ");
        System.out.println("Enter C for a new custom game");
        System.out.println();
        System.out.println("Enter H for the highscores");
        System.out.println();
        System.out.println("Enter Q to quit the game");
        String input = scanner.nextLine().toLowerCase();

        switch (input){
            case "s":
                try{
                    Game g = new Game(10, 10, 10);
                    g.start();
                }catch  (Exception e){
                    System.out.println(e);
                }
                break;
            case "m":
                try{
                    Game g = new Game(20, 20, 20);
                    g.start();
                }catch  (Exception e){
                    System.out.println(e);
                }
                break;

            case "l":
                try{
                    Game g = new Game(40, 40, 40);
                    g.start();
                }catch  (Exception e){
                    System.out.println(e);
                }
                break;
            case "c":
                customGame();
                break;
            case "h":
                //start Highscore screen
                break;
            case "q":
                System.exit(0);
                break;
            default:
                ScreenCleaner.clearConsole();
                Menu.chooseOption();
        }

    }
    public static void customGame(){
        System.out.println("Please enter the height of the game");
        int height = saveIntInput();
        System.out.println("Please enter the width of the game");
        int width = saveIntInput();
        System.out.println("Please enter the amount of bombs for the game");
        int bombs = saveIntInput();

        Game g = new Game(height, width, bombs);
        try {
            g.start();
        } catch (TooManyBombsException e) {
            System.out.println("Please select fewer Bombs");
            customGame();
        }


    }
    private static int saveIntInput(){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            return saveIntInput();
        }
    }
}
