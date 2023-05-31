import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private Renderer renderer;
    private Matrix matrix;
    private Cursor cursor;
    private int height;
    private int width;
    private int amountOfBombs;
    private WinChecker winchecker;
    private boolean started = false;
    private Stopwatch stopwatch = new Stopwatch();

    // Constructor for the Game class
    public Game(int height, int width, int amountOfBombs) throws TooManyBombsException {
        // Setting the height, width and amount of bombs as per the passed parameters
        this.height = height;
        this.width = width;
        this.amountOfBombs = amountOfBombs;

        // Initializing the cursor based on the provided width and height
        cursor = new Cursor(width,height);

        // Initializing the game matrix with the provided width, height and amount of bombs
        matrix = new Matrix(width,height,amountOfBombs);

        // Initializing the renderer to render the game matrix and the cursor
        renderer = new Renderer(matrix,cursor);

        // Initializing the winchecker with the game matrix
        winchecker = new WinChecker(matrix);
    }

    // Method to start the game
    public void start() {
        // Start the stopwatch
        stopwatch.start();

        // Display the initial frame
        displayFrame();

        // Set the game as started
        started = true;
    }

    // Method to check if the player has won
    private void checkWin(){
        // If the win checker's check method returns true, trigger the win sequence
        if(winchecker.check()){
            win();
        }
    }

    // Method to stop the key listener
    private void stopKeyListener(){
        try {
            // Attempt to unregister the native hook of the global screen
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            // Throw a new runtime exception if an exception occurs
            throw new RuntimeException(e);
        }
    }
    private void win(){
        // Stop the KeyListener
        KeyListener.setRunning(false);

        // Clear the screen
        clearScreen();

        // Display a congratulatory message in ASCII Art

        System.out.println("                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "YYYYYYY       YYYYYYY                                     WWWWWWWW                           WWWWWWWW                                \n" +
                "Y:::::Y       Y:::::Y                                     W::::::W                           W::::::W                                \n" +
                "Y:::::Y       Y:::::Y                                     W::::::W                           W::::::W                                \n" +
                "Y::::::Y     Y::::::Y                                     W::::::W                           W::::::W                                \n" +
                "YYY:::::Y   Y:::::YYYooooooooooo   uuuuuu    uuuuuu        W:::::W           WWWWW           W:::::W ooooooooooo   nnnn  nnnnnnnn    \n" +
                "   Y:::::Y Y:::::Y oo:::::::::::oo u::::u    u::::u         W:::::W         W:::::W         W:::::Woo:::::::::::oo n:::nn::::::::nn  \n" +
                "    Y:::::Y:::::Y o:::::::::::::::ou::::u    u::::u          W:::::W       W:::::::W       W:::::Wo:::::::::::::::on::::::::::::::nn \n" +
                "     Y:::::::::Y  o:::::ooooo:::::ou::::u    u::::u           W:::::W     W:::::::::W     W:::::W o:::::ooooo:::::onn:::::::::::::::n\n" +
                "      Y:::::::Y   o::::o     o::::ou::::u    u::::u            W:::::W   W:::::W:::::W   W:::::W  o::::o     o::::o  n:::::nnnn:::::n\n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u             W:::::W W:::::W W:::::W W:::::W   o::::o     o::::o  n::::n    n::::n\n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u              W:::::W:::::W   W:::::W:::::W    o::::o     o::::o  n::::n    n::::n\n" +
                "       Y:::::Y    o::::o     o::::ou:::::uuuu:::::u               W:::::::::W     W:::::::::W     o::::o     o::::o  n::::n    n::::n\n" +
                "       Y:::::Y    o:::::ooooo:::::ou:::::::::::::::uu              W:::::::W       W:::::::W      o:::::ooooo:::::o  n::::n    n::::n\n" +
                "    YYYY:::::YYYY o:::::::::::::::o u:::::::::::::::u               W:::::W         W:::::W       o:::::::::::::::o  n::::n    n::::n\n" +
                "    Y:::::::::::Y  oo:::::::::::oo   uu::::::::uu:::u                W:::W           W:::W         oo:::::::::::oo   n::::n    n::::n\n" +
                "    YYYYYYYYYYYYY    ooooooooooo       uuuuuuuu  uuuu                 WWW             WWW            ooooooooooo     nnnnnn    nnnnnn\n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     "
        );

        // Wait for user input (fixes some weird behavior)
        new Scanner(System.in).nextLine();

        // Create a new scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask for the player's name to save the score
        System.out.println("Please enter your name to save the score:");
        String input = scanner.nextLine();
        System.out.println(input);

        // Create a new ScoreHandler and save the score with the player's name and the elapsed time
        new ScoreHandler().newScore(input, stopwatch.stop());

        // Show a question or a menu after the game ends
        gameOverMenu();
    }
    // Method to get the current renderer
    public Renderer getRenderer() {
        return renderer;
    }

    // Method to get the current cursor
    public Cursor getCursor() {
        // Return the current cursor if the game has started
        if(started){
            return cursor;
        }

        // Return a new cursor at position (1,1) if the game has not started
        return new Cursor(1,1);
    }

    // Method to open the cell at the current cursor position
    void openCellAtCursor(){
        // Check if the game has started
        if (started){
            // Get the cursor's coordinates
            int x = cursor.getX();
            int y = cursor.getY();

            // Open the cell at the cursor's position
            openCell(x,y);
        }
    }
    // This function marks a cell on the board
    void mark(){
        // Only allow marking if the game has started
        if(started){
            // Get the position of the cursor
            int x = cursor.getX();
            int y = cursor.getY();

            // Retrieve the cell from the matrix at cursor's position
            Cell cell = matrix.getCell(x,y);

            // Only allow marking if the cell is not visible
            if(!cell.isVisible()){
                // If the cell is flagged, unflag it
                if(cell.isFlagged()){
                    cell.setFlagged(false);
                    matrix.decreaseFlagAmount();
                }
                else
                {
                    // Else flag the cell if there are bombs left to be flagged
                    if (matrix.getBombAmount()>=matrix.getFlagAmount()+1){
                        cell.setFlagged(true);
                        matrix.increaseFlagAmount();
                    }
                }
            }

            // After each marking, check if the player has won
            checkWin();
        }
    }

    // This function opens a cell at a given position
    private void openCell(int x, int y){
        // Retrieve the cell from the matrix at given position
        Cell c = matrix.getCell(x,y);

        // Only open cell if it's not visible
        if (!c.isVisible()){
            // If the cell is a bomb, trigger the lose function
            if (c.getType()== Cell.Type.BOMB){
                lose();
            }

            // Make cell visible
            c.setVisible(true);

            // If the cell is a number and its value is zero, open all surrounding cells
            if(c.getType()== Cell.Type.NUMBER){
                if (c.getValue()==0){
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if(!(j==0&&i==0)){
                                openCell(x+i,y+j);
                            }
                        }
                    }
                }
            }
        }

        // After opening a cell, check if the player has won
        checkWin();
    }

    // Function that defines what should happen when the player loses
    private void lose(){
        // Clear the screen
        clearScreen();

        // Stop the KeyListener
        KeyListener.setRunning(false);

        // Print a losing message in ASCII Art
        System.out.println("                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "YYYYYYY       YYYYYYY                                     LLLLLLLLLLL                                                        tttt          \n" +
                "Y:::::Y       Y:::::Y                                     L:::::::::L                                                     ttt:::t          \n" +
                "Y:::::Y       Y:::::Y                                     L:::::::::L                                                     t:::::t          \n" +
                "Y::::::Y     Y::::::Y                                     LL:::::::LL                                                     t:::::t          \n" +
                "YYY:::::Y   Y:::::YYYooooooooooo   uuuuuu    uuuuuu         L:::::L                  ooooooooooo       ssssssssss   ttttttt:::::ttttttt    \n" +
                "   Y:::::Y Y:::::Y oo:::::::::::oo u::::u    u::::u         L:::::L                oo:::::::::::oo   ss::::::::::s  t:::::::::::::::::t    \n" +
                "    Y:::::Y:::::Y o:::::::::::::::ou::::u    u::::u         L:::::L               o:::::::::::::::oss:::::::::::::s t:::::::::::::::::t    \n" +
                "     Y:::::::::Y  o:::::ooooo:::::ou::::u    u::::u         L:::::L               o:::::ooooo:::::os::::::ssss:::::stttttt:::::::tttttt    \n" +
                "      Y:::::::Y   o::::o     o::::ou::::u    u::::u         L:::::L               o::::o     o::::o s:::::s  ssssss       t:::::t          \n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u         L:::::L               o::::o     o::::o   s::::::s            t:::::t          \n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u         L:::::L               o::::o     o::::o      s::::::s         t:::::t          \n" +
                "       Y:::::Y    o::::o     o::::ou:::::uuuu:::::u         L:::::L         LLLLLLo::::o     o::::ossssss   s:::::s       t:::::t    tttttt\n" +
                "       Y:::::Y    o:::::ooooo:::::ou:::::::::::::::uu     LL:::::::LLLLLLLLL:::::Lo:::::ooooo:::::os:::::ssss::::::s      t::::::tttt:::::t\n" +
                "    YYYY:::::YYYY o:::::::::::::::o u:::::::::::::::u     L::::::::::::::::::::::Lo:::::::::::::::os::::::::::::::s       tt::::::::::::::t\n" +
                "    Y:::::::::::Y  oo:::::::::::oo   uu::::::::uu:::u     L::::::::::::::::::::::L oo:::::::::::oo  s:::::::::::ss          tt:::::::::::tt\n" +
                "    YYYYYYYYYYYYY    ooooooooooo       uuuuuuuu  uuuu     LLLLLLLLLLLLLLLLLLLLLLLL   ooooooooooo     sssssssssss              ttttttttttt  \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           "
        );

        // Wait for user input (fixes some weird behavior)
        Scanner scanner = new Scanner(System.in);
        // Prompt the user to press enter to continue
        System.out.println("Press Enter to continue");
        scanner.nextLine();

        // Show a question or a menu after the game ends
        gameOverMenu();
    }
    // This method displays the current state of the game
    public void displayFrame(){
        // Only display the frame if the game has started
        if (started){
            // Clear the console screen
            clearScreen();

            // Print the current game frame rendered by the renderer
            System.out.println(renderer.renderFrame());
        }
    }

    // This method clears the console screen
    public void clearScreen(){
        // Uses a utility class, ScreenCleaner, to clear the console
        ScreenCleaner.clearConsole();
    }

    // This method prompts the user with a menu after the game ends
    private void gameOverMenu() {
        // Clear the console screen
        clearScreen();

        // Create a Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);

        // Show the options to the user
        System.out.println("Enter r to restart, m for the main menu or q to quit");

        // Read the user's input
        String input = scanner.nextLine().toUpperCase();

        // Check the user's input and execute the corresponding action
        if (input.equals("M")) {
            // If user enters "M", go to the main menu
            Menu.chooseOption();
        }
        else if (input.equals("R")) {
            // If user enters "R", restart the game with the same settings
            Menu.restartGame(height,width, amountOfBombs);
        }
        else if (input.equals("Q")) {
            // If user enters "Q", stop the key listener and exit the program
            stopKeyListener();
            exit(0);
        } else {
            // If the user enters anything else, show the question again
            gameOverMenu();
        }
    }


}
