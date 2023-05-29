public class WinChecker {
    private Cell[][] cells;
    private Matrix m;

    public WinChecker(Matrix m) {
        this.m = m;
        this.cells = m.getCells();
    }
    public boolean check(){
        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {
                Cell c = m.getCell(i,j);
                if (!(c.isVisible()||c.isFlagged())) {
                    return false;
                }
            }
            
        }
        return true;
    }
}
