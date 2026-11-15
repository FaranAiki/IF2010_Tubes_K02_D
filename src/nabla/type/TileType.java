package nabla.type;

/**
 * Jenis tile/lingkungan pada map.
 * <p>
 * Mengikuti F07.2 - Legenda simbol.
 */
public enum TileType {
    /** Tile yang dapat dilalui ('.'). */
    WALKABLE('.'),
    /** Air - memperlambat player 50% ('W'). */
    WATER('W'),
    /** Lava - siklus DORMANT/WARNING/ERUPTING ('H'). */
    LAVA('H'),
    /** Es - player slide sesuai arah terakhir ('I'). */
    ICE('I'),
    /** Laser - aktif/nonaktif, damage 1 nyawa ('L'). */
    LASER('L'),
    /** Saklar - toggle Gate/Laser ('S'). */
    SWITCH('S'),
    /** Gerak tertutup - hentikan gerakan & ledakan ('G'). */
    GATE_CLOSED('G'),
    /** Gerak terbuka - dapat dilalui ('g'). */
    GATE_OPEN('g'),
    /** Block tidak bisa dihancurkan ('#'). */
    INDESTRUCTIBLE('#'),
    /** Block bisa dihancurkan ('D'). */
    DESTRUCTIBLE('D'),
    /** Block terkuat (hanya Pumped Bomb) ('R'). */
    REINFORCED('R'),
    /** Spawn player ('P'). */
    SPAWN_PLAYER('P'),
    /** Spawn enemy ('E'). */
    SPAWN_ENEMY('E'),
    /** Spawn boss ('B'). */
    SPAWN_BOSS('B'),
    /** Pintu keluar ('X'). */
    EXIT('X'),
    /** Tempat setelah block hancur. */
    EMPTY('.');

    private final char symbol;

    TileType(char symbol) {
        this.symbol = symbol;
    }

    public char tileToChar() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public static TileType charToTile(char c) {
        for (TileType type : values()) {
            if (type.symbol == c) {
                return type;
            }
        }
        return WALKABLE;
    }
}
