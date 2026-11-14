package nabla.type;

/**
 * Jenis bom yang dapat dipilih pemain.
 * <p>
 * Mengikuti F02.2 - Jenis Bomb.
 */
public enum BombType {
    /** Ledak 3 detik, jangkauan mengikuti blastRange player (F02.2.1). */
    NORMAL,
    /** Ledak 4,5 detik, jangkauan +2 (max 7), hancurkan Reinforced Block (F02.2.2). */
    PUMPED,
    /** Ledak manual, perlu Remote Detonator (F02.2.3). */
    REMOTE
}