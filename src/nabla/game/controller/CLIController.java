package nabla.game.controller;

import nabla.game.GameLoop;
import nabla.model.entity.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;
import nabla.util.Direction;

import java.util.Scanner;

/**
 * CLI Controller -- menangani input dari terminal.
 * <p>
 * <b>Prinsip</b> Single Responsibility. Ini hanya mengurus CLI input handling.
 * Map loading didelegasikan ke MapLoader.
 */
public class CLIController extends AbstractController {
    private final Scanner scanner;
    private final MapLoader mapLoader;

    public CLIController(Game game, GameLoop gameLoop, IRenderer renderer, Scanner scanner, MapLoader mapLoader) {
        super(game, gameLoop, renderer);
        this.scanner = scanner;
        this.mapLoader = mapLoader;
    }

    @Override
    protected void handleMenu() {
        renderer.renderMenu();
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> loadDefaultMap();
            case "2" -> {
                renderer.renderHowToPlay();
                scanner.nextLine(); /* tunggu Enter */
            }
            case "3" -> running = false;
            default -> renderer.renderInvalidChoice();
        }
    }

    private void loadDefaultMap() {
        mapLoader.loadDefaultMap(game);
    }

    @Override
    protected void handlePlaying() {
        renderer.renderGame(game);
        String input = scanner.nextLine().trim().toLowerCase();
        handleInput(input);
    }

    @Override
    protected void handlePaused() {
        renderer.renderPaused();
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "p" -> setGameState(GameState.PLAYING);
            case "r" -> game.restart();
            case "esc" -> setGameState(GameState.MENU);
        }
    }

    @Override
    protected void handleGameOver(GameState state) {
        renderer.renderGameOver(state);
        renderer.renderGameOverOptions();
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "r" -> game.restart();
            case "esc" -> setGameState(GameState.MENU);
        }
    }

    @Override
    public void handleInput(String input) {
        if (input.isEmpty()) return;

        char c = input.charAt(0);
        switch (c) {
            case 'w' -> game.getPlayer().move(Direction.UP, (pos, dir) -> true);
            case 's' -> game.getPlayer().move(Direction.DOWN, (pos, dir) -> true);
            case 'a' -> game.getPlayer().move(Direction.LEFT, (pos, dir) -> true);
            case 'd' -> game.getPlayer().move(Direction.RIGHT, (pos, dir) -> true);
            case 'b' -> game.getPlayer().placeBomb();
            case 'e' -> cycleBombType();
            case 'q' -> { /* Detonasi Remote Bomb - handled by GameLoop */ }
            case 'p' -> setGameState(GameState.PAUSED);
            case 'r' -> game.restart();
            case '\u001b' -> setGameState(GameState.MENU); /* ESC */
        }
    }

    private void cycleBombType() {
        var current = game.getPlayer().getSelectedBombType();
        var values = nabla.type.BombType.values();
        int idx = (current.ordinal() + 1) % values.length;
        if (game.getPlayer().isRemoteUnlocked() || values[idx] != nabla.type.BombType.REMOTE) {
            game.getPlayer().setSelectedBombType(values[idx]);
        }
    }
}
