package nabla.util;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    /* Akan diasumsikan bahwa arah hanya bisa berjarak 1 dan tidak boleh diagonal. */
    public final int deltaRow;
    public final int deltaCol;

    Direction(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    /* Arah berlawanan */
    public Direction opposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            default: return this;
        }
    }

    /* Arah diputar 90 derajat searah jarum jam */
    public Direction rotateClockwise() {
        switch (this) {
            case UP: return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return LEFT;
            case LEFT: return UP;
            default: return this;
        }
    }

    /* Arah diputar 270 derajat searah jarum jam */
    public Direction rotateCounterClockwise() {
        switch (this) {
            case UP: return LEFT;
            case LEFT: return DOWN;
            case DOWN: return RIGHT;
            case RIGHT: return UP;
            default: return this;
        }
    }

    /* Helper mengubah Direction -> Position */
    public Position deltaPosition() {
        return new Position(deltaRow, deltaCol);
    }

    @Override
    public String toString() {
        return switch (this) {
            case UP -> "Atas";
            case DOWN -> "Bawah";
            case LEFT -> "Kiri";
            case RIGHT -> "Kanan";
        };
    }
}
