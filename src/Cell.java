public class Cell {
    enum Type{
        BOMB,
        NUMBER,
        UNSET
    }
    private int value;
    private Type type;
    private boolean visible = false;

    public Cell(Type type) {
        this.type = type;
    }

    public Cell() {
        this.type = Type.UNSET;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }
}
