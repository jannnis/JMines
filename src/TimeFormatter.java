// Class to handle formatting of time durations
public class TimeFormatter {

    // Method to format a time duration
    public static String formatDuration(int totalSeconds) {
        // Calculate the number of hours by dividing the total seconds by 3600 (seconds in an hour)
        int hours = totalSeconds / 3600;

        // Calculate the remaining minutes by getting the remainder of total seconds divided by 3600,
        // and then divide it by 60 (seconds in a minute)
        int minutes = (totalSeconds % 3600) / 60;

        // Calculate the remaining seconds by getting the remainder of total seconds divided by 60
        int seconds = totalSeconds % 60;

        // If there is at least one hour, return the formatted string with hours, minutes and seconds
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            // If there are no hours, return the formatted string with just minutes and seconds
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}