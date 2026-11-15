package nabla.controller;

import nabla.type.GameState;

/**
 * Interface untuk game controller.
 * <p>
 * <b>Design Pattern Interface Segregation</b> yaitu hanya method yang dibutuhkan
 * untuk mengontrol game loop dan menangani input pemain.
 * Implementasi berbeda untuk CLI, JavaFX, dan Swing sehingga ini sangat modular.
 */
public interface IController {
    /** Jalankan controller. */
    void run();

    /** Hentikan controller. */
    void stop();

    /** Tangani input dari pemain. */
    void handleInput(String input);

    /** Dapatkan state game saat ini. */
    GameState getGameState();

    /** Set state game. */
    void setGameState(GameState state);
}