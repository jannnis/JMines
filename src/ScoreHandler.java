import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScoreHandler {
    private CsvHandler csvHandler;
    public ScoreHandler() {
        String userHomeDirectory = System.getProperty("user.home");
        String filePath = userHomeDirectory + File.separator + "highscore.csv";

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        csvHandler = new CsvHandler(filePath);
    }
    public void newScore(String name, int duration){
        csvHandler.readEntries();
        csvHandler.addEntry(name,duration);
        csvHandler.saveEntries();
    }
    public String highscoreList(int numberOfEntries){
        String out = "";
        List<Entry> entries = csvHandler.getLowestEntries(numberOfEntries);
        for (Entry entry : entries) {
            out += entry.toString();
            out += "\n";
        }
        return out;
    }

}
