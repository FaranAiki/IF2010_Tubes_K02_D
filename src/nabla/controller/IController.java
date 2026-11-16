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
 * TODO: keknya IController & IRenderer bisa dilebur jadi ISceneHandler
 */
public interface IController {
    void start();

    void stop();

    boolean isRunning();

    GameState getGameState();

    void setGameState(GameState state);

    /* TODO: kalau mau nambah scene harus mengatur Controller di sini */

    void handleMenu();

    void handleHowToPlay();

    void handlePlaying();

    void handlePaused();

    void handleGameOver(GameState state);

    void handleInput();
}
