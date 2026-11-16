package nabla.controller;

import nabla.type.GameState;

/**
 * Interface untuk game controller.
 * <p>
 * <b>Design Pattern Interface Segregation</b> dan juga Controller dalam MVC, yaitu hanya method yang dibutuhkan
 * untuk mengontrol game loop dan menangani input pemain.
 * Implementasi berbeda untuk CLI, JavaFX, dan Swing sehingga ini sangat modular.
 * <p>
 * Setiap IController adalah thread-nya sendiri melalui {@link #start()} dan {@link #stop()},
 * mirip dengan GameLoop dan IRenderer.
 */
public interface IController {
    /** Memulai thread controller dan menjalankan loop kontrol. */
    void start();

    /** Menghentikan thread controller. */
    void stop();

    /** Mengecek apakah controller sedang berjalan. */
    boolean isRunning();

    void handleInput(String input);

    GameState getGameState();

    void setGameState(GameState state);
}