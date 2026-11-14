package nabla.type;

/**
 * Jenis bom yang dapat dipilih pemain.
 * Mengikuti F02.2 - Jenis Bomb.
 */
public enum BombType {
    NORMAL,   /* F02.2.1 — ledak 3s, range mengikuti blastRange player */
    PUMPED,   /* F02.2.2 — ledak 4.5s, range +2, max 7, hancurkan Reinforced */
    REMOTE    /* F02.2.3 — ledak manual, perlu Remote Detonator */
}