package nabla.ui.swing;

import nabla.ui.AbstractRenderer;
import nabla.game.Game;
import nabla.type.GameState;

/**
 * Swing Renderer -- tampilan GUI Swing (placeholder).
 * <p>
 * Implementasi IRenderer untuk Swing. Semua method masih stub dan
 * akan diisi sesuai kebutuhan UI Swing nantinya.
 */
public class SwingRenderer extends AbstractRenderer {

    public SwingRenderer(Game game) {
        super(game);
    }

    @Override
    public void renderMenu() {
        // TODO: Swing menu panel
    }

    @Override
    public void renderHowToPlay() {
        // TODO: Swing how-to-play panel
    }

    @Override
    public void renderGame(Game game) {
        // TODO: Swing game panel
    }

    @Override
    public void renderGameOver(GameState state) {
        // TODO: Swing game over panel
    }

    @Override
    public void renderGameOverOptions() {
        // TODO: Swing opsi setelah game over
    }

    @Override
    public void renderInvalidChoice() {
        // TODO: Swing pesan pilihan tidak valid
    }

    @Override
    public void renderPaused() {
        // TODO: Swing layar pause
    }

    @Override
    public void renderExitMessage() {
        // TODO: Swing pesan keluar
    }
}