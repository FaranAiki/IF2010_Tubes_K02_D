package nabla.ui;

import nabla.game.Game;
import nabla.type.GameState;

/**
 * Interface untuk renderer game.
 * <p>
 * Mengikuti prinsip Interface Segregation, yaitu hanya method yang dibutuhkan
 * untuk rendering (menu, game, game over) sehingga bisa dipanggil
 * oleh Main dan lain-lain tanpa harus mementingkan atribut.
 * <p>
 * Renderer bertanggung jawab penuh atas siklus hidup rendering-nya sendiri
 * ({@link #start()}/{@link #stop()}) dan membaca state permainan dari
 * {@link Game}. Controller tidak perlu tahu-menahu soal rendering.
 */
public interface IRenderer {
    void start();

    void stop();

    boolean isRunning();

    Game getGame();

    void renderMenu();

    void renderHowToPlay();

    void renderGame(Game game);

    void renderGameOver(GameState state);

    void renderGameOverOptions();

    void renderPaused();

    void renderInvalidChoice();

    void renderExitMessage();
}
