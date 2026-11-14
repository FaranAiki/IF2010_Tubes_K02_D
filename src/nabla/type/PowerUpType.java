package nabla.type;

/**
 * Jenis Power-Up yang dapat muncul di map.
 * <p>
 * Mengikuti F06.2 - Jenis Power-Up.
 */
public enum PowerUpType {
    /** Tambah kapasitas bom (+1, max 3). */
    BOMB_UP,
    /** Tambah jangkauan ledakan (+1, max 5). */
    FIRE_UP,
    /** Tambah kecepatan (+50%, max +100%). */
    SPEED_UP,
    /** Buka Remote Bomb. */
    REMOTE_DETONATOR,
    /** Tambah nyawa (+1, max 5). */
    HEART
}