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
    /** How to play. */
    HOW_TO_PLAY,
    /** Pemenang. */
    WIN,
    /** Kalah. */
    LOSE
}