public class Cursor {
    private int x =0;
    private int y =0;
    private int maxX;
    private int maxY;
    private boolean showCursor = true;

    public Cursor(int maxX, int maxY, int x, int y ) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public Cursor(int maxX, int maxY ) {
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public void makeValidPosition(){
        if(x<0){
            x = 0;
        }
        if(y<0){
            y = 0;
        }
        if(this.x>=maxX){
            x = maxX-1;
        }
        if(y>=maxY){
            y = maxY-1;
        }
        setShowCursor(true);
    }
    public void moveUp(){
        y--;
        makeValidPosition();
    }
    public void moveDown(){
        y++;
        makeValidPosition();
    }
    public void moveLeft(){
        x--;
        makeValidPosition();
    }
    public void moveRight(){
        x++;
        makeValidPosition();
    }

    public void setX(int x) {
        this.x = x;
        makeValidPosition();
    }

    public void setY(int y) {
        this.y = y;
        makeValidPosition();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isShowCursor() {
        return showCursor;
    }

    public void setShowCursor(boolean showCursor) {
        this.showCursor = showCursor;
    }
}
