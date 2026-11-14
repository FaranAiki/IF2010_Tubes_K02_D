package nabla.game.controller;

import nabla.game.GameLoop;
import nabla.model.entity.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;

/**
 * Swing Controller -- placeholder untuk Swing implementation.
 * <p>
 * TODO: Implementasikan menggunakan Swing KeyListener, Timer, dll.
 * Controller ini akan di-inject dengan Swing-specific components.
 */
public class SwingController extends AbstractController {
    public SwingController(Game game, GameLoop gameLoop, IRenderer renderer) {
        super(game, gameLoop, renderer);
        /* TODO: Setup Swing KeyListener, javax.swing.Timer, dll. */
    }

    @Override
    protected void handleMenu() {
        /* TODO: Swing menu handling - pake JMenuBar/JPanel */
        throw new UnsupportedOperationException("SwingController not yet implemented");
    }

    @Override
    protected void handlePlaying() {
        /* TODO: Swing game loop - pake javax.swing.Timer */
        throw new UnsupportedOperationException("SwingController not yet implemented");
    }

    @Override
    protected void handlePaused() {
        /* TODO: Swing pause handling */
        throw new UnsupportedOperationException("SwingController not yet implemented");
    }

    @Override
    protected void handleGameOver(GameState state) {
        /* TODO: Swing game over handling */
        throw new UnsupportedOperationException("SwingController not yet implemented");
    }

    @Override
    public void handleInput(String input) {
        /* TODO: Swing input handling - pake KeyListener */
        throw new UnsupportedOperationException("SwingController not yet implemented");
    }
}