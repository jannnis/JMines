import org.junit.Test;
import static org.junit.Assert.*;
class TimeFormatterTest {

    @Test
    public void testFormatDuration_lessThanAnHour() {
        // Arrange
        int totalSeconds = 600;  // 10 minutes
        String expected = "10:00";

        // Act
        String result = TimeFormatter.formatDuration(totalSeconds);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFormatDuration_moreThanAnHour() {
        // Arrange
        int totalSeconds = 3666;  // 1 hour, 1 minute and 6 seconds
        String expected = "01:01:06";

        // Act
        String result = TimeFormatter.formatDuration(totalSeconds);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testFormatDuration_noSeconds() {
        // Arrange
        int totalSeconds = 0;
        String expected = "00:00";

        // Act
        String result = TimeFormatter.formatDuration(totalSeconds);

        // Assert
        assertEquals(expected, result);
    }
}