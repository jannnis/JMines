public class TooManyBombsException extends Exception {
    public TooManyBombsException() {
        super("More Bombs than this Matrix can handle");
    }
}
