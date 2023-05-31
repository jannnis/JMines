import java.io.*;
import java.util.*;

// Class to handle reading and writing entries to/from a CSV file
public class CsvHandler {
    private String filename;  // Name of the file to read/write entries
    private List<Entry> entries;  // List to store entries

    // Constructor, takes filename as input
    public CsvHandler(String filename) {
        this.filename = filename;
        this.entries = new ArrayList<>();
    }

    // Method to read entries from the CSV file
    public void readEntries() {
        // Using try-with-resources to automatically close the BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read file line by line
            while ((line = reader.readLine()) != null) {
                // Split each line into parts using the comma as separator
                String[] parts = line.split(",");
                String name = parts[0];
                int duration = Integer.parseInt(parts[1]);
                // Add entry to the list
                entries.add(new Entry(name, duration));
            }
            // Sort the list after reading all entries
            Collections.sort(entries);
        } catch (IOException e) {
            // Print the stack trace for debugging purposes in case of an error
            e.printStackTrace();
        }
    }

    // Method to add an entry to the list and sort it
    public void addEntry(String name, int duration) {
        entries.add(new Entry(name, duration));
        Collections.sort(entries);
    }

    // Method to save all entries from the list to the file
    public void saveEntries() {
        // Using try-with-resources to automatically close the BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Loop over each entry in the list
            for (Entry entry : entries) {
                // Write entry to file and go to new line
                writer.write(entry.getName() + "," + entry.getDuration());
                writer.newLine();
            }
        } catch (IOException e) {
            // Print the stack trace for debugging purposes in case of an error
            e.printStackTrace();
        }
    }

    // Method to return the first n entries with the lowest durations
    public List<Entry> getLowestEntries(int n) {
        // Sort the list of entries
        Collections.sort(entries);

        // If n is greater than or equal to the number of entries, return all entries
        if (n >= entries.size()) {
            return entries;
        }
        // Otherwise, return the first n entries
        return entries.subList(0, n);
    }
}
