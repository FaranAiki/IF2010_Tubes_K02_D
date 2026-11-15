package nabla.type;

/**
 * Jenis block pada map.
 * <p>
 * Mengikuti F04.2 sesuai dengan Jenis Block.
 */
public enum BlockType {
    /** Tidak bisa dihancurkan, menghentikan ledakan (F04.2.1). */
    INDESTRUCTIBLE,
    /** Bisa dihancurkan oleh ledakan, bisa menyimpan Power-Up (F04.2.2). */
    DESTRUCTIBLE,
    /** Hanya bisa dihancurkan oleh Pumped Bomb (F04.2.3). */
    REINFORCED
}
