package nabla.controller;

import nabla.game.GameLoop;
import nabla.game.MapLoader;
import nabla.game.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;

/**
 * Swing Controller placeholder untuk Swing implementation.
 * <p>
 * TODO: Implementasikan menggunakan Swing KeyListener, Timer, dll.
 * Controller ini akan di-inject dengan Swing-specific components.
 */
public class SwingController extends AbstractController {
    public SwingController(Game game, GameLoop gameLoop, IRenderer renderer, MapLoader mapLoader) {
        super(game, gameLoop, renderer);
    }

    @Override
    protected void handleMenu() {
    }

    @Override
    protected void handlePlaying() {
    }

    @Override
    protected void handlePaused() {
    }

    @Override
    protected void handleGameOver(GameState state) {
    }

    @Override
    public void handleInput(String input) {
    }
}
