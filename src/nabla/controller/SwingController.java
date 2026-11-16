package nabla.controller;

import nabla.game.Game;
import nabla.type.GameState;

/**
 * Swing Controller placeholder untuk Swing implementation.
 * <p>
 * TODO: Implementasikan menggunakan Swing KeyListener, Timer, dll.
 * Controller ini akan di-inject dengan Swing-specific components.
 */
public class SwingController extends AbstractController {
    public SwingController(Game game) {
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
