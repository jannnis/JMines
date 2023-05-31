// Class to handle console screen clearing
public class ScreenCleaner {
    // Method to clear the console screen
    public static void clearConsole() {
        try {
            // Get the operating system name
            final String os = System.getProperty("os.name");

            // Check if the operating system is a version of Windows
            if (os.contains("Windows")) {
                // If it is, use the Windows command "cls" to clear the console
                // The command is executed in a new process
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // If the OS is not Windows (for example, Linux or Mac), 
                // use an ANSI escape code to clear the console
                System.out.print("\033[H\033[2J");
            }
        } catch (final Exception e) {
            // If there's an exception, print its message to the error output stream
            System.err.println(e.getMessage());
        }
    }
}