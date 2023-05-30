public class Stopwatch {
    private long start;

    public Stopwatch() {
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public int stop() {
        long elapsedMillis = System.currentTimeMillis() - start;
        start = 0;
        return (int) (elapsedMillis / 1000);
    }
}