package nabla.type;

/**
 * State keseluruhan permainan.
 * <p>
 * Sesuai dengan F09.2
 */
public enum GameState {
    /** Menu utama. */
    MENU,
    /** Sedang bermain. */
    PLAYING,
    /** Permainan jeda. */
    PAUSED,
    /** Pemenang. */
    WIN,
    /** Kalah. */
    LOSE
}
