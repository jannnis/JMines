// import required libraries
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.exit;

// This class is responsible for handling key inputs from the user
public class KeyListener {
    private static Game game;

    // Initialize the game
    static {
        try {
            game = new Game(10,10,1);
        } catch (TooManyBombsException e) {
            // Throw exception if too many bombs are added to the game
            throw new RuntimeException(e);
        }
    }

    private static boolean running = false;

    // Method to set the current game
    public static void setGame(Game g) {
        game = g;
    }

    // Method to check if the game is running
    public static boolean IsRunning() {
        return running;
    }

    // Method to set the running state of the game
    public static void setRunning(boolean running) {
        KeyListener.running = running;
    }

    // Method to start the KeyListener
    public static void start(){
        try {
            // Register the native hook to capture keyboard events
            GlobalScreen.registerNativeHook();
            // Add a native key listener
            GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
                // Override methods for key events
                @Override
                public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
                }

                @Override
                public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
                    // Get the current cursor position in the game
                    Cursor cursor = game.getCursor();
                    // Handle different key inputs
                    switch (nativeEvent.getKeyCode()){
                        case 17:
                        case 57416:
                            // move up
                            cursor.moveUp();
                            break;
                        case 30:
                        case 57419:
                            // move left
                            cursor.moveLeft();
                            break;
                        case 31:
                        case 57424:
                            // move down
                            cursor.moveDown();
                            break;
                        case 32:
                        case 57421:
                            // move right
                            cursor.moveRight();
                            break;
                        case 28:
                            // open cell at the cursor's current location
                            game.openCellAtCursor();
                            break;
                        case 14:
                            // mark a cell in the game
                            game.mark();
                            break;
                    }
                    // Update the game display after each action
                    game.displayFrame();
                }
            });
        } catch (NativeHookException ex) {
            // Error handling for exceptions in registering the native hook
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            exit(1);
        }
    }
}
