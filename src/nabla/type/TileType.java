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
    EMPTY;

    public static TileType charToTile(char c) {
        switch (c) {
            case '#': return TileType.INDESTRUCTIBLE;
            case 'D': return TileType.DESTRUCTIBLE;
            case 'R': return TileType.REINFORCED;
            case '.': return TileType.WALKABLE;
            case 'P': return TileType.SPAWN_PLAYER;
            case 'E': return TileType.SPAWN_ENEMY;
            case 'B': return TileType.SPAWN_BOSS;
            case 'X': return TileType.EXIT;
            case 'W': return TileType.WATER;
            case 'S': return TileType.SWITCH;
            case 'G': return TileType.GATE_CLOSED;
            case 'g': return TileType.GATE_OPEN;
            case 'H': return TileType.LAVA;
            case 'I': return TileType.ICE;
            case 'L': return TileType.LASER;
            default: return TileType.WALKABLE;
        }
    }
}
