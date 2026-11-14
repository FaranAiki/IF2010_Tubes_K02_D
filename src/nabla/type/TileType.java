package nabla.type;

/**
 * Jenis tile/lingkungan pada map.
 * <p>
 * Mengikuti F07.2 - Legenda simbol.
 */
public enum TileType {
    /** Tile yang dapat dilalui ('.'). */
    WALKABLE,
    /** Air - memperlambat player 50% ('W'). */
    WATER,
    /** Lava - siklus DORMANT/WARNING/ERUPTING ('H'). */
    LAVA,
    /** Es - player slide sesuai arah terakhir ('I'). */
    ICE,
    /** Laser - aktif/nonaktif, damage 1 nyawa ('L'). */
    LASER,
    /** Saklar - toggle Gate/Laser ('S'). */
    SWITCH,
    /** Gerak tertutup - hentikan gerakan & ledakan ('G'). */
    GATE_CLOSED,
    /** Gerak terbuka - dapat dilalui ('g'). */
    GATE_OPEN,
    /** Block tidak bisa dihancurkan ('#'). */
    INDESTRUCTIBLE,
    /** Block bisa dihancurkan ('D'). */
    DESTRUCTIBLE,
    /** Block terkuat (hanya Pumped Bomb) ('R'). */
    REINFORCED,
    /** Spawn player ('P'). */
    SPAWN_PLAYER,
    /** Spawn enemy ('E'). */
    SPAWN_ENEMY,
    /** Spawn boss ('B'). */
    SPAWN_BOSS,
    /** Pintu keluar ('X'). */
    EXIT,
    /** Tempat setelah block hancur. */
    EMPTY
}