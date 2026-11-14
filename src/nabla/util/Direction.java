package nabla.util;

/**
 * Arah gerak pada grid map (atas, bawah, kiri, kanan).
 * <p>
 * Setiap arah menyimpan delta baris dan kolom untuk perhitungan posisi baru.
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int deltaRow;
    public final int deltaCol;

    Direction(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

    public Direction rotateClockwise() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    public Direction rotateCounterClockwise() {
        return switch (this) {
            case UP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
        };
    }

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
