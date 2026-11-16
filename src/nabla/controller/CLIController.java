package nabla.controller;

import nabla.game.MapLoader;
import nabla.model.collision.CollisionChecker;
import nabla.game.Game;
import nabla.type.GameState;
import nabla.util.Direction;

import java.util.Scanner;

/**
 * CLI Controller menangani input dari terminal.
 * <p>
 * <b>Design Pattern Single Responsibility</b> Ini hanya mengurus CLI input handling.
 * Map loading didelegasikan ke MapLoader.
 */
public class CLIController extends AbstractController {
    public CLIController(Game game) {
      super(game);
    }

    public void handleMenu() {
    }

    public void handleHowToPlay() {
    }

    public void handlePlaying() {
    }

    public void handlePaused() {
    }

    public void handleGameOver(GameState state) {
    }

    public void handleInput() {
    }

    private void cycleBombType() {
    }
}
