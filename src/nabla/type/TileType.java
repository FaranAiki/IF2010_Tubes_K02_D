package nabla.type;

/**
 * Jenis tile/lingkungan pada map.
 * Mengikuti F07.2 - Legenda simbol.
 */
public enum TileType {
    WALKABLE,      /* . */
    WATER,         /* W — memperlambat player 50% */
    LAVA,          /* H — siklus DORMANT/WARNING/ERUPTING */
    ICE,           /* I — player slide sesuai arah terakhir */
    LASER,         /* L — aktif/nonaktif, damage 1 nyawa */
    SWITCH,        /* S — toggle Gate/Laser */
    GATE_CLOSED,   /* G — hentikan gerakan & ledakan */
    GATE_OPEN,     /* g — dilewati */
    INDESTRUCTIBLE, /* # — block */
    DESTRUCTIBLE,   /* D — block */
    REINFORCED,     /* R — block (hanya Pumped Bomb) */
    SPAWN_PLAYER,  /* P */
    SPAWN_ENEMY,   /* E */
    SPAWN_BOSS,    /* B */
    EXIT,          /* X */
    EMPTY          /* tempat setelah block hancur */
}