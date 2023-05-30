public class Entry implements Comparable<Entry> {
    private String name;
    private int duration;

    public Entry(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
    @Override
    public String toString(){
        String out;
        out = getName()+": "+TimeFormatter.formatDuration(getDuration());
        return out;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Entry other) {
        return Integer.compare(this.duration, other.duration);
    }
}
