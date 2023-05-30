import java.io.*;
import java.util.*;

public class CsvHandler {
    private String filename;
    private List<Entry> entries;

    public CsvHandler(String filename) {
        this.filename = filename;
        this.entries = new ArrayList<>();
    }

    public void readEntries() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int duration = Integer.parseInt(parts[1]);
                entries.add(new Entry(name, duration));
            }
            Collections.sort(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(String name, int duration) {
        entries.add(new Entry(name, duration));
        Collections.sort(entries);
    }

    public void saveEntries() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Entry entry : entries) {
                writer.write(entry.getName() + "," + entry.getDuration());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Entry> getLowestEntries(int n) {
        // Create a copy of the entries list
        Collections.sort(entries);

        if (n >= entries.size()) {
            // If n is greater than or equal to the number of entries, return all entries
            return entries;
        }
        // Otherwise, return the first n entries
        return entries.subList(0, n);
    }



}
