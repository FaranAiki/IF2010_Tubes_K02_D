package nabla.ui;

import nabla.model.entity.Game;
import nabla.type.GameState;

/**
 * Interface untuk renderer game.
 * Mengikuti prinsip Interface Segregation, yaitu hanya method yang dibutuhkan
 * untuk rendering (menu, game, game over) sehingga bisa dipanggil
 * oleh Main dan lain-lain tanpa harus mementingkan atribut.
 */
public interface IRenderer {
    /** Render menu utama. */
    void renderMenu();

    /** Render petunjuk bermain. */
    void renderHowToPlay();

    /** Render state permainan saat ini. */
    void renderGame(Game game);

    /** Render layar game over (WIN/LOSE). */
    void renderGameOver(GameState state);

    /** Render pesan pilihan tidak valid. */
    void renderInvalidChoice();

    /** Render layar pause. */
    void renderPaused();

    /** Render opsi setelah game over. */
    void renderGameOverOptions();

    /** Render pesan keluar. */
    void renderExitMessage();
}
