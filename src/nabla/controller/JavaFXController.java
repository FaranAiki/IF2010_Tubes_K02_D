package nabla.controller;

import nabla.game.Game;
import nabla.type.GameState;

/**
 * JavaFX Controller placeholder untuk JavaFX implementation.
 * <p>
 * TODO: Implementasikan menggunakan JavaFX event handlers (KeyEvent, dll.)
 * Controller ini akan di-inject dengan JavaFX-specific components.
 */
public class JavaFXController extends AbstractController {
    public JavaFXController(Game game) {
        super(game);
    }

    public void handleMenu() {
    }

    public void handleHowToPlay() {
        setGameState(GameState.MENU);
    }

    public void handlePlaying() {
    }

    public void handlePaused() {
    }

    public void handleGameOver(GameState state) {
    }

    public void handleInput() {
    }
}
