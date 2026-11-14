package nabla.util;

/**
 * Arah gerak pada grid map (atas, bawah, kiri, kanan).
 * <p>
 * <b>Prinsip Value Object / Enum</b>. Arah hanya bisa berjarak 1 dan tidak boleh diagonal.
 * Setiap arah menyimpan delta baris dan kolom untuk perhitungan posisi baru.
 */
public enum Direction {
    /** Gerak ke atas (baris berkurang 1). */
    UP(-1, 0),
    /** Gerak ke bawah (baris bertambah 1). */
    DOWN(1, 0),
    /** Gerak ke kiri (kolom berkurang 1). */
    LEFT(0, -1),
    /** Gerak ke kanan (kolom bertambah 1). */
    RIGHT(0, 1);

    /** Selisih baris untuk arah ini. */
    public final int deltaRow;
    /** Selisih kolom untuk arah ini. */
    public final int deltaCol;

    /**
     * Konstruktor Direction.
     *
     * @param deltaRow selisih baris
     * @param deltaCol selisih kolom
     */
    Direction(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    /**
     * Mendapatkan arah berlawanan dari arah ini.
     *
     * @return arah berlawanan (misalkan UP -> DOWN, LEFT -> RIGHT)
     */
    public Direction opposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            default: return this;
        }
    }

    /**
     * Memutar arah 90 derajat searah jarum jam.
     *
     * @return arah setelah diputar (misalkan UP -> RIGHT)
     */
    public Direction rotateClockwise() {
        switch (this) {
            case UP: return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return LEFT;
            case LEFT: return UP;
            default: return this;
        }
    }

    /**
     * Memutar arah 270 derajat searah jarum jam (atau 90 derajat berlawanan jarum jam).
     *
     * @return arah setelah diputar (misalkan UP -> LEFT)
     */
    public Direction rotateCounterClockwise() {
        switch (this) {
            case UP: return LEFT;
            case LEFT: return DOWN;
            case DOWN: return RIGHT;
            case RIGHT: return UP;
            default: return this;
        }
    }

    /**
     * Mengubah Direction menjadi Position (delta baris dan kolom).
     *
     * @return Position baru dengan nilai deltaRow dan deltaCol
     */
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
