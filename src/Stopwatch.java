public class Stopwatch {
    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long stop() {
        long elapsedMillis = System.currentTimeMillis() - start;
        start = 0;
        return elapsedMillis / 1000;
    }
}