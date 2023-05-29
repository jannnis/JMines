import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Game {
    private Renderer renderer;
    private Matrix matrix;
    private Cursor cursor;
    private int height;
    private int width;
    private int amountOfBombs;
    private WinChecker winchecker;
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
        listenToKeys();

    }
    private void checkWin(){
        if(winchecker.check()){
            win();
        }
    }
    private void win(){


        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        clearScreen();
        new GameFin().win();
        System.out.println("you won!");
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

                    System.out.println(nativeEvent.getKeyCode());
                    renderer.renderFrame();
                    displayFrame();
                }
            });
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
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
        if (matrix.getBombAmount()>=matrix.getFlagAmount()){
            matrix.getCell(x,y).setFlagged(true);
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


        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        clearScreen();
        new GameFin().lose();
        System.out.println("you lost!");
    }
    public void displayFrame(){
        clearScreen();
        System.out.println(renderer.renderFrame());
    }
    public void clearScreen(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
