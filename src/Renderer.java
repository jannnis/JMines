// Defines the Renderer class, which is responsible for displaying the current state of the game
public class Renderer {
    // Instance variables for the game's matrix and cursor
    private Matrix matrix;
    private Cursor cursor;

    // Constructor for the Renderer class, which takes a Matrix and Cursor as parameters
    public Renderer(Matrix matrix, Cursor cursor) {
        this.matrix = matrix;
        this.cursor = cursor;
    }

    // Method for rendering the current game frame
    public String renderFrame(){
        // Create a 2D String array to represent each cell in the game matrix
        String[][] lines = new String[matrix.getHeight()][matrix.getWidth()];

        // Populate the lines array with the appropriate symbols for each cell
        for (int i = 0; i < matrix.getHeight(); i++) {
            lines[i] = renderLine(i);
        }

        // If the cursor is visible, add it to the lines array
        if(cursor.isShowCursor()){
            lines = renderCursor(lines);
        }

        // Convert the lines array to a single String and return it
        return LinesToString(lines);
    }

    // Method for rendering a single line of the game matrix
    public String[] renderLine(int line){
        int width = matrix.getWidth();
        String out[] = new String[width];

        // For each cell in the line, get its symbol and add it to the output array
        for (int i = 0; i < width; i++) {
            Cell cell = matrix.getCell(i,line);
            out[i] = renderSymbol(cell);
        }
        return out;
    }

    // Method for adding the cursor symbol to the lines array
    public String[][] renderCursor(String[][] lines){
        lines[cursor.getY()][cursor.getX()] = "#";
        return lines;
    }

    // Method for getting the appropriate symbol for a cell
    public String renderSymbol(Cell cell){
        // If the cell is flagged, return the flag symbol
        if(cell.isFlagged()){
            return "⚑";
        }

        // If the cell is visible, return the appropriate symbol based on its type
        if(cell.isVisible()){
            if(cell.getType() == Cell.Type.NUMBER){
                return String.valueOf(cell.getValue());
            }
            if(cell.getType()== Cell.Type.BOMB){
                return "B";
            }
        }

        // If the cell is not flagged or visible, return the default symbol
        return  "▓";
    }

    // Method for converting the lines array to a single String
    public String LinesToString(String[][] lines){
        String str = "";

        // For each line in the lines array, add each symbol to the output string
        for (String[] line: lines) {
            for (String symbol: line) {
                str += symbol;
            }
            str += "\n";
        }
        return str;
    }
}
