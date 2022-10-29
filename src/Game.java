public class Game {
    private Renderer renderer;
    private Matrix matrix;
    private Cursor cursor;
    private int height;
    private int width;
    private int amountOfBombs;
    public Game(int height, int width, int amountOfBombs) {
        this.height = height;
        this.width = width;
        this.amountOfBombs = amountOfBombs;
    }
    public void start() throws TooManyBombsException {
        cursor = new Cursor(width,height);
        matrix = new Matrix(width,height,amountOfBombs);
        renderer = new Renderer(matrix,cursor);
    }
}
