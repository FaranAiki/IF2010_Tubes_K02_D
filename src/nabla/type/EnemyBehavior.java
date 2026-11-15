package nabla.type;

/**
 * Perilaku Normal Enemy.
 * <p>
 * Mengikuti F05.2 - Normal Enemy.
 */
public enum EnemyBehavior {
    /** Selalu bergerak mengikuti jalur patrol. */
    PATROL,
    /** PATROL ketika player jauh, CHASE ketika player dalam 3 tile. (pakai distanceTo) */
    CHASE
}
