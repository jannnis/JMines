import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

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
    private Stopwatch stopwatch = new Stopwatch();

    public Game(int height, int width, int amountOfBombs) {
        this.height = height;
        this.width = width;
        this.amountOfBombs = amountOfBombs;

    }
    public void start() throws TooManyBombsException {
        cursor = new Cursor(width,height);
        matrix = new Matrix(width,height,amountOfBombs);
        renderer = new Renderer(matrix,cursor);
        winchecker = new WinChecker(matrix);
        stopwatch.start();
        listenToKeys();

        displayFrame();
    }
    private void checkWin(){
        if(winchecker.check()){
            win();
        }
    }
    private void stopKeyListener(){

        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }
    private void win(){
        System.out.println("you won!");
        clearScreen();

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
        new Scanner(System.in).nextLine(); //fixes some weird behaviour
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name to save the score:");
        String input = scanner.nextLine();
        System.out.println(input);
        new ScoreHandler().newScore(input,stopwatch.stop());
        showQuestion();
    }
    private void listenToKeys(){
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
                @Override
                public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
                }

                @Override
                public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
                    switch (nativeEvent.getKeyCode()){
                        case 17:
                        case 57416:
                            cursor.moveUp();
                            break;
                        case 30:
                        case 57419:
                            cursor.moveLeft();
                            break;
                        case 31:
                        case 57424:
                            cursor.moveDown();
                            break;
                        case 32:
                        case 57421:
                            cursor.moveRight();
                            break;
                        case 28:
                            openCellAtCursor();
                            break;
                        case 14:
                            mark();
                            break;
                    }
                    renderer.renderFrame();
                    displayFrame();
                }
            });
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            exit(1);
        }
    }
    private void openCellAtCursor(){
        int x = cursor.getX();
        int y = cursor.getY();
        openCell(x,y);
    }
    private void mark(){
        int x = cursor.getX();
        int y = cursor.getY();
        Cell cell = matrix.getCell(x,y);
            if(!cell.isVisible()){
                if(cell.isFlagged()){
                    cell.setFlagged(false);
                    matrix.decreaseFlagAmount();
                }
                else
                {

                    if (matrix.getBombAmount()>=matrix.getFlagAmount()+1){
                        cell.setFlagged(true);
                        matrix.increaseFlagAmount();

                    }
                }

            }

        checkWin();
    }
    private void openCell(int x, int y){
        Cell c;
        c = matrix.getCell(x,y);
        if (!c.isVisible()){
            if (c.getType()== Cell.Type.BOMB){
                lose();
            }
            c.setVisible(true);
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
        checkWin();
    }
    private void lose(){
        //show lose screen
        clearScreen();
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
        showQuestion();
    }
    public void displayFrame(){
        clearScreen();
        System.out.println(renderer.renderFrame());
    }
    public void clearScreen(){
        ScreenCleaner.clearConsole();
    }
    private void showQuestion() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter r to restart, m for the main menu or q to quit");
        String input = scanner.nextLine().toUpperCase();  // Read user input
                            if (input.equals("M")) {
                                //will be implemented
                            }
                            if (input.equals("R")) {

                            }
                            if (input.equals("Q")) {
                                stopKeyListener();
                                exit(0);

                            } else {
                                showQuestion();
                            }
                }


}
