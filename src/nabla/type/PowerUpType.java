package nabla.type;

/**
 * Jenis Power-Up yang dapat muncul di map.
 * Mengikuti F06.2 - Jenis Power-Up.
 */
public enum PowerUpType {
    BOMB_UP,         /* +1 bombCapacity (max 3) */
    FIRE_UP,         /* +1 blastRange (max 5) */
    SPEED_UP,        /* kecepatan +50% (max +100%) */
    REMOTE_DETONATOR, /* buka Remote Bomb */
    HEART            /* +1 nyawa (max 5) */
}