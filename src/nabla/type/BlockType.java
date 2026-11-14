package nabla.type;

/**
 * Jenis block pada map.
 * Mengikuti F04.2 - Jenis Block.
 */
public enum BlockType {
    INDESTRUCTIBLE,  /* F04.2.1 — tidak bisa dihancurkan, hentikan ledakan */
    DESTRUCTIBLE,    /* F04.2.2 — bisa dihancurkan oleh ledakan, bisa simpan Power-Up */
    REINFORCED       /* F04.2.3 — hanya bisa dihancurkan oleh Pumped Bomb */
}