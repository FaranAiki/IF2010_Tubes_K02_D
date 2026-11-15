package nabla.ui;

import nabla.game.Game;
import nabla.type.GameState;

/**
 * Interface untuk renderer game.
 * <p>
 * Mengikuti prinsip Interface Segregation, yaitu hanya method yang dibutuhkan
 * untuk rendering (menu, game, game over) sehingga bisa dipanggil
 * oleh Main dan lain-lain tanpa harus mementingkan atribut.
 */
public interface IRenderer {
    void renderMenu();

    void renderHowToPlay();

    void renderGame(Game game);

    void renderGameOver(GameState state);

    void renderInvalidChoice();

    void renderPaused();

    void renderGameOverOptions();

    void renderExitMessage();
}
