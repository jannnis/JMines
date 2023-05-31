// Represents a single cell in the game
public class Cell {
    // An enum to represent the types of a cell
    enum Type{
        BOMB,
        NUMBER,
        UNSET
    }

    // Value for a cell (useful for cells of NUMBER type)
    private int value;
    // Type of the cell - can be BOMB, NUMBER, or UNSET
    private Type type;
    // Indicates if a cell is visible or not
    private boolean visible = false;
    // Indicates if a cell is flagged or not
    private boolean flagged = false;

    // Constructor to create a cell of a specific type
    public Cell(Type type) {
        this.type = type;
    }

    // Default constructor creates an UNSET type cell
    public Cell() {
        this.type = Type.UNSET;
    }

    // Setter method to set the type of a cell
    public void setType(Type type) {
        this.type = type;
    }

    // Setter method to set the visibility of a cell
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // Setter method to set the value of a cell
    public void setValue(int value) {
        this.value = value;
    }

    // Getter method to get the value of a cell
    public int getValue() {
        return value;
    }

    // Getter method to get the type of a cell
    public Type getType() {
        return type;
    }

    // Method to check if a cell is visible
    public boolean isVisible() {
        return visible;
    }

    // Method to check if a cell is flagged
    public boolean isFlagged() {
        return flagged;
    }

    // Setter method to set a cell as flagged or unflagged
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
