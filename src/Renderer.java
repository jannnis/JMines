public class Renderer {
    private Matrix matrix;
    private Cursor cursor;
    public Renderer(Matrix matrix, Cursor cursor) {
        this.matrix = matrix;
        this.cursor = cursor;
    }
    public String renderFrame(){
        String[][] lines = new String[matrix.getHeight()][matrix.getWidth()];
        for (int i = 0; i < matrix.getHeight(); i++) {
            lines[i] = renderLine(i);
        }
        if(cursor.isShowCursor()){
            lines = renderCursor(lines);
            cursor.setShowCursor(false);
        }else {
            cursor.setShowCursor(true);
        }
        return LinesToString(lines);
    }
    public String[] renderLine(int line){
        int width = matrix.getWidth();
        String out[] = new String[width];
        for (int i = 0; i < width; i++) {
            Cell cell = matrix.getCell(i,line);
            out[i] = renderSymbol(cell);
        }
        return out;
    }
    public String[][] renderCursor(String[][] lines){
        lines[cursor.getY()][cursor.getX()] = "#";
        return lines;
    }
    public String renderSymbol(Cell cell){
        if(cell.isFlagged()){
            return "⚑";
        }
        if(cell.isVisible()){
            if(cell.getType() == Cell.Type.NUMBER){
                return String.valueOf(cell.getValue());
            }
            if(cell.getType()== Cell.Type.BOMB){
                return "BOMB";
            }
        }
        return  "▓";
    }
    public String LinesToString(String[][] lines){
        String str = "";
        for (String[] line: lines) {
            for (String symbol: line) {
                str += symbol;
            }
            str += "\n";
        }
        return str;
    }
}
