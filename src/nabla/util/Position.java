package nabla.util;

import java.util.Objects;

/**
 * Posisi pada grid map (baris, kolom).
 * <p>
 * <b>Prinsip Value Object / Immutable-ish</b>. Digunakan oleh Entity, Bomb, PowerUp,
 * dan kelas lain untuk menyimpan lokasi di map tanpa memaksa hierarki inheritance.
 */
public final class Position {
    private int row;
    private int col;

    /**
     * Konstruktor Position.
     *
     * @param row indeks baris
     * @param col indeks kolom
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Mendapatkan indeks baris.
     *
     * @return indeks baris
     */
    public int getRow() { return row; }

    /**
     * Mendapatkan indeks kolom.
     *
     * @return indeks kolom
     */
    public int getCol() { return col; }

    /**
     * Mengatur indeks baris.
     *
     * @param row indeks baris baru
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Mengatur indeks kolom.
     *
     * @param col indeks kolom baru
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Geser posisi sesuai arah yang diberikan.
     *
     * @param dir arah gerakan
     */
    public void move(Direction dir) {
        this.row += dir.deltaRow;
        this.col += dir.deltaCol;
    }

    /**
     * Tambahkan posisi dengan offset.
     *
     * @param offset posisi offset yang ditambahkan
     * @return Position baru hasil penjumlahan
     */
    public Position add(Position offset) {
        return new Position(this.row + offset.row, this.col + offset.col);
    }

    /**
     * Kurangi posisi dengan offset.
     *
     * @param offset posisi offset yang dikurangkan
     * @return Position baru hasil pengurangan
     */
    public Position sub(Position offset) {
        return new Position(this.row - offset.row, this.col - offset.col);
    }

    /**
     * Hitung jarak Taxicab (Manhattan) ke posisi lain.
     *
     * @param other posisi tujuan
     * @return jarak Taxicab antara posisi ini dan posisi tujuan
     */
    public int distanceTo(Position other) {
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
    }

    /**
     * Cek apakah berada pada baris yang sama dengan posisi lain.
     *
     * @param other posisi yang dibandingkan
     * @return true jika baris sama
     */
    public boolean isSameRow(Position other) {
        return this.row == other.row;
    }

    /**
     * Cek apakah berada pada kolom yang sama dengan posisi lain.
     *
     * @param other posisi yang dibandingkan
     * @return true jika kolom sama
     */
    public boolean isSameCol(Position other) {
        return this.col == other.col;
    }

    /**
     * Cek apakah berada pada tile yang sama dengan posisi lain.
     *
     * @param other posisi yang dibandingkan
     * @return true jika baris dan kolom sama
     */
    public boolean isSameTile(Position other) {
        return isSameRow(other) && isSameCol(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        return isSameTile((Position) o);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
