package nabla.controller;

import nabla.type.GameState;

/**
 * Interface untuk game controller.
 * <p>
 * <b>Design Pattern Interface Segregation</b> dan juga Controller dalam MVC, yaitu hanya method yang dibutuhkan
 * untuk mengontrol game loop dan menangani input pemain.
 * Implementasi berbeda untuk CLI, JavaFX, dan Swing sehingga ini sangat modular.
 */
public interface IController {
    void run();

    void stop();

    void handleInput(String input);

    GameState getGameState();

    void setGameState(GameState state);
}
