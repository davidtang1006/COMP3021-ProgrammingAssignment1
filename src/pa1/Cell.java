package pa1;

/**
 * An immutable class that represents a cell in the grid map
 */
// TODO
public class Cell {
    final private int x;
    final private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object o) {
        Cell c;
        if (o instanceof Cell) {
            c = (Cell) o;
            return (x == c.getX()) && (y == c.getY());
        }
        return false;
    }
}