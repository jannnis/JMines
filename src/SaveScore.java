import java.io.File;
import java.io.IOException;

public class SaveScore {
    private CsvHandler csvHandler;
    public SaveScore() {
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

}
