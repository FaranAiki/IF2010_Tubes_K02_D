package nabla.controller;

import nabla.game.Game;
import nabla.game.MapLoader;
import nabla.type.GameState;
import nabla.util.Direction;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Scanner;

public class CLIController extends AbstractController {
    private MapLoader mapLoader = new MapLoader();

    private Terminal getTerminal() {
        return nabla.ui.cli.ConsoleRenderer.sharedTerminal;
    }

    public CLIController(Game game) {
        super(game);
    }

    @Override
    protected void onStart() {
        mapLoader.loadDefaultMap(game);
    }

    public void handleMenu() {
        try {
            KeyStroke key = getTerminal().readInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.Enter) {
                    setGameState(GameState.PLAYING);
                } else if (key.getKeyType() == KeyType.Escape || (key.getCharacter() != null && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))) {
                    running = false;
                }
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public void handleHowToPlay() {
        setGameState(GameState.MENU);
    }

    public void handlePlaying() {
        try {
            KeyStroke key = getTerminal().readInput();
            if (key == null) return;
            if (key.getKeyType() == KeyType.ArrowUp || (key.getCharacter() != null && (key.getCharacter() == 'w' || key.getCharacter() == 'W'))) {
                game.getPlayer().move(Direction.UP, game.getCollisionChecker());
            } else if (key.getKeyType() == KeyType.ArrowDown || (key.getCharacter() != null && (key.getCharacter() == 's' || key.getCharacter() == 'S'))) {
                game.getPlayer().move(Direction.DOWN, game.getCollisionChecker());
            } else if (key.getKeyType() == KeyType.ArrowLeft || (key.getCharacter() != null && (key.getCharacter() == 'a' || key.getCharacter() == 'A'))) {
                game.getPlayer().move(Direction.LEFT, game.getCollisionChecker());
            } else if (key.getKeyType() == KeyType.ArrowRight || (key.getCharacter() != null && (key.getCharacter() == 'd' || key.getCharacter() == 'D'))) {
                game.getPlayer().move(Direction.RIGHT, game.getCollisionChecker());
            } else if (key.getCharacter() != null && key.getCharacter() == ' ') {
                game.placeBomb();
            } else if (key.getCharacter() != null && (key.getCharacter() == 'p' || key.getCharacter() == 'P')) {
                setGameState(GameState.PAUSED);
            } else if (key.getCharacter() != null && (key.getCharacter() == 'q' || key.getCharacter() == 'Q')) {
                running = false;
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public void handlePaused() {
        try {
            KeyStroke key = getTerminal().readInput();
            if (key != null && key.getCharacter() != null && (key.getCharacter() == 'p' || key.getCharacter() == 'P')) {
                setGameState(GameState.PLAYING);
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public void handleGameOver(GameState state) {
        try {
            KeyStroke key = getTerminal().readInput();
            if (key != null && key.getCharacter() != null) {
                if (key.getCharacter() == 'r' || key.getCharacter() == 'R') {
                    game.restart();
                    setGameState(GameState.PLAYING);
                } else if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') {
                    running = false;
                }
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public void handleInput() {
    }

    private void cycleBombType() {
    }

    @Override
    protected void shutdown() {
        // terminal shared with renderer; do not close here
    }
}
