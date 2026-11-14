package nabla.game.controller;

import nabla.game.GameLoop;
import nabla.model.entity.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;

/**
 * JavaFX Controller -- placeholder untuk JavaFX implementation.
 * <p>
 * TODO: Implementasikan menggunakan JavaFX event handlers (KeyEvent, dll.)
 * Controller ini akan di-inject dengan JavaFX-specific components.
 */
public class JavaFXController extends AbstractController {
    public JavaFXController(Game game, GameLoop gameLoop, IRenderer renderer) {
        super(game, gameLoop, renderer);
        /* TODO: Setup JavaFX event handlers, AnimationTimer, dll. */
    }

    @Override
    protected void handleMenu() {
        /* TODO: JavaFX menu handling - pake FXML/SceneBuilder */
        throw new UnsupportedOperationException("JavaFXController not yet implemented");
    }

    @Override
    protected void handlePlaying() {
        /* TODO: JavaFX game loop - pake AnimationTimer */
        throw new UnsupportedOperationException("JavaFXController not yet implemented");
    }

    @Override
    protected void handlePaused() {
        /* TODO: JavaFX pause handling */
        throw new UnsupportedOperationException("JavaFXController not yet implemented");
    }

    @Override
    protected void handleGameOver(GameState state) {
        /* TODO: JavaFX game over handling */
        throw new UnsupportedOperationException("JavaFXController not yet implemented");
    }

    @Override
    public void handleInput(String input) {
        /* TODO: JavaFX input handling - pake KeyEvent */
        throw new UnsupportedOperationException("JavaFXController not yet implemented");
    }
}