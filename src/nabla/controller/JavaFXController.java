package nabla.controller;

import nabla.game.GameLoop;
import nabla.game.MapLoader;
import nabla.game.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;

/**
 * JavaFX Controller placeholder untuk JavaFX implementation.
 * <p>
 * TODO: Implementasikan menggunakan JavaFX event handlers (KeyEvent, dll.)
 * Controller ini akan di-inject dengan JavaFX-specific components.
 */
public class JavaFXController extends AbstractController {
    public JavaFXController(Game game, GameLoop gameLoop, IRenderer renderer, MapLoader mapLoader) {
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
