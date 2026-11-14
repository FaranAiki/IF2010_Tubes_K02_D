package nabla.type;

/**
 * Perilaku Normal Enemy.
 * Mengikuti F05.2 - Normal Enemy.
 */
public enum EnemyBehavior {
    PATROL,  /* selalu bergerak mengikuti jalur patrol */
    CHASE    /* PATROL ketika player jauh, CHASE ketika player dalam 3 tile */
}