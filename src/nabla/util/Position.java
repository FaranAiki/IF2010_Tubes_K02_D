package nabla.util;

import java.util.Objects;

public final class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void move(Direction dir) {
        this.row += dir.deltaRow;
        this.col += dir.deltaCol;
    }

    public Position add(Position offset) {
        return new Position(this.row + offset.row, this.col + offset.col);
    }

    public Position sub(Position offset) {
        return new Position(this.row - offset.row, this.col - offset.col);
    }

    public int distanceTo(Position other) {
        /* Jarak Taxicab karena jarak Euclidean does not make any sense */
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
    }

    public boolean isSameRow(Position other) {
        return this.row == other.row;
    }

    public boolean isSameCol(Position other) {
        return this.col == other.col;
    }

    public boolean isSameTile(Position other) {
        return isSameRow(other) && isSameCol(other);
    }

    @Override
    public boolean equals(Object o) {
        /* Untuk optimisasi karena tinggal cek apakah memori sama or not */
        if (this == o) return true;

        /* Untuk optimisasi memori */
        if (!(o instanceof Position)) return false;

        /* o adalah instanceof Position, maka */
        return isSameTile((Position) o);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
