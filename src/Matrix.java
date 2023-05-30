import java.util.ArrayList;

public class Matrix {
    private Cell[][] cells;
    private int height;
    private int width;
    private int bombAmount;
    private int flagAmount = 0;

    public int getFlagAmount() {
        System.out.println(flagAmount);
        return flagAmount;
    }
    public void increaseFlagAmount(){
        flagAmount++;
    }
    public void decreaseFlagAmount(){
        flagAmount--;
    }
    public int getBombAmount() {
        return bombAmount;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Matrix(int width, int height, int amountOfBombs) throws TooManyBombsException {
        this.bombAmount = amountOfBombs;
        cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
        this.width = width;
        this.height = height;
        generateBombs(amountOfBombs);
        generateNumbers();
    }
    public void generateBombs(int amount) throws TooManyBombsException {
        checkBombAmount(amount);

        ArrayList<Integer> positions = new ArrayList<Integer>();
        for(int i = 0;i<getCellAmount();i++){
            positions.add(i);
        }
        //make sure the upper left corner is free of bombs
        positions.remove(width);
        positions.remove(width);
        positions.remove(0);
        positions.remove(0);

        for (int i = 0; i < amount; i++)
        {
            int index = (int)(Math.random() * positions.size());
            setBombFormPosition(positions.get(index));
            positions.remove(index);
        }
    }
    public void generateNumbers(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell c = getCell(x,y);
                if(c.getType() != Cell.Type.BOMB){
                    int neighbouringBombs = countNeighbouringBombs(x,y);
                    c.setType(Cell.Type.NUMBER);
                    c.setValue(neighbouringBombs);
                }
            }
        }
    }
    public int countNeighbouringBombs(int x, int y){
        int n = 0;
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                Cell c = getCell(i,j);
                if (c.getType()== Cell.Type.BOMB){
                    n++;
                }
            }
            
        }
        return n;
    }
    public void checkBombAmount(int amount) throws TooManyBombsException {
        if(getCellAmount()-4 < amount){
            throw new TooManyBombsException();
        }
    }
    public int getCellAmount(){
        return width*height;
    }
    public void setBombFormPosition(int position){
        int column = position%width;
        int row = (int)position/width;
        cells[row][column].setType(Cell.Type.BOMB);
    }
    public Cell getCell(int x, int y){
        if(isValidX(x)&&isValidY(y)){
             return cells[y][x];
        }
        return new Cell();
    }
    public boolean isValidX(int x){
        if(x<0){
            return false;
        }
        if(x>=width){
            return false;
        }
        return true;
    }
    public boolean isValidY(int y){
        if(y<0){
            return false;
        }
        if(y>=height){
            return false;
        }
        return true;
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
