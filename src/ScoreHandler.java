import java.io.File;
import java.io.IOException;
import java.util.List;

// Class to handle the scores
public class ScoreHandler {
    private CsvHandler csvHandler; // CsvHandler to interact with a CSV file

    // Constructor
    public ScoreHandler() {
        // Get the user's home directory path
        String userHomeDirectory = System.getProperty("user.home");

        // Path where highscore CSV file will be located
        String filePath = userHomeDirectory + File.separator + "highscore.csv";

        File file = new File(filePath);

        // If the file does not exist yet, create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // Print stack trace if an exception occurs
                e.printStackTrace();
            }
        }
        // Initialize CsvHandler with the file path
        csvHandler = new CsvHandler(filePath);
    }

    // Method to add a new score
    public void newScore(String name, int duration){
        // Read existing entries
        csvHandler.readEntries();

        // Add new entry
        csvHandler.addEntry(name, duration);

        // Save entries to the CSV file
        csvHandler.saveEntries();
    }

    // Method to get the highscore list
    public String highscoreList(int numberOfEntries){
        // Read entries from the CSV file
        csvHandler.readEntries();

        String out = "";
        // Get the lowest entries (indicating fastest times/highscores)
        List<Entry> entries = csvHandler.getLowestEntries(numberOfEntries);

        // Concatenate each entry to the output string
        for (Entry entry : entries) {
            out += entry.toString();
            out += "\n";
        }

        // Return the highscore list
        return out;
    }
}