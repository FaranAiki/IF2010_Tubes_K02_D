package nabla.type;

/**
 * State keseluruhan permainan.
 * <p>
 * Mengikuti F09.2 - Game State.
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