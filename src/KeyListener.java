import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.exit;

public class KeyListener {
    private static Game game;

    static {
        try {
            game = new Game(10,10,1);
        } catch (TooManyBombsException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean running = false;

    public static void setGame(Game g) {
        game = g;

    }

    public static boolean IsRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        KeyListener.running = running;
    }

    public static void start(){
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
                        Cursor cursor = game.getCursor();
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
                            game.openCellAtCursor();
                            break;
                        case 14:
                            game.mark();
                            break;
                    }
                    game.displayFrame();

                }
            });
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            exit(1);
        }
    }
}
