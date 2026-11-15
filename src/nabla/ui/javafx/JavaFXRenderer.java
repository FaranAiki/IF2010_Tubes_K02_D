package nabla.ui.javafx;

import nabla.game.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;

/**
 * JavaFX Renderer -- tampilan GUI JavaFX (placeholder).
 * <p>
 * Implementasi IRenderer untuk JavaFX. Semua method masih stub dan
 * akan diisi sesuai kebutuhan UI JavaFX nantinya.
 */
public class JavaFXRenderer implements IRenderer {
    private final Game game;

    public JavaFXRenderer(Game game) {
        this.game = game;
    }

    @Override
    public void renderMenu() {
        // TODO: JavaFX menu scene
    }

    @Override
    public void renderHowToPlay() {
        // TODO: JavaFX how-to-play scene
    }

    @Override
    public void renderGame(Game game) {
        // TODO: JavaFX game scene
    }

    @Override
    public void renderGameOver(GameState state) {
        // TODO: JavaFX game over scene
    }

    @Override
    public void renderInvalidChoice() {
        // TODO: JavaFX pesan pilihan tidak valid
    }

    @Override
    public void renderPaused() {
        // TODO: JavaFX layar pause
    }

    @Override
    public void renderGameOverOptions() {
        // TODO: JavaFX opsi setelah game over
    }

    @Override
    public void renderExitMessage() {
        // TODO: JavaFX pesan keluar
    }
}
