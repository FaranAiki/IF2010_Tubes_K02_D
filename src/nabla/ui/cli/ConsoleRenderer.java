package nabla.ui.cli;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import nabla.ui.AbstractRenderer;
import nabla.game.Game;
import nabla.model.Entity;
import nabla.model.Player;
import nabla.model.block.Block;
import nabla.model.bomb.Bomb;
import nabla.model.bomb.Explosion;
import nabla.model.enemy.NormalEnemy;
import nabla.model.powerup.PowerUp;
import nabla.model.world.Map;
import nabla.type.GameState;
import nabla.type.TileType;
import nabla.util.Position;

public class ConsoleRenderer extends AbstractRenderer {
    public static Terminal sharedTerminal;
    public static Screen sharedScreen;
    private Terminal terminal;
    private Screen screen;

    public ConsoleRenderer(Game game) {
        super(game);
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            sharedTerminal = terminal;
            sharedScreen = screen;
            screen.startScreen();
            screen.setCursorPosition(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renderMenu() {
        try {
            screen.clear();
            TextGraphics g = screen.newTextGraphics();
            g.setForegroundColor(TextColor.ANSI.WHITE);
            g.putString(2, 2, "=== NANGOR BLAST ===");
            g.putString(2, 4, "WASD: move, SPACE: place bomb, P: pause, Q: quit");
            g.putString(2, 5, "Press ENTER to start playing...");
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    @Override
    public void renderHowToPlay() {
        try {
            screen.clear();
            TextGraphics g = screen.newTextGraphics();
            g.setForegroundColor(TextColor.ANSI.WHITE);
            g.putString(2, 2, "=== HOW TO PLAY ===");
            g.putString(2, 4, "Move: WASD / Arrow keys");
            g.putString(2, 5, "Place bomb: SPACE");
            g.putString(2, 6, "Pause: P");
            g.putString(2, 7, "Quit: Q");
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    @Override
    public void renderGame(Game game) {
        try {
            screen.clear();
            TextGraphics g = screen.newTextGraphics();
            Map map = game.getCurrentMap();
            if (map == null) {
                screen.refresh();
                return;
            }
            int rows = map.getHeight();
            int cols = map.getWidth();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    char ch = tileToChar(r, c, map);
                    g.setForegroundColor(TextColor.ANSI.WHITE);
                    g.putString(c, r, String.valueOf(ch));
                }
            }
            // render bombs
            for (Bomb b : game.getBombs()) {
                if (b.isActive()) {
                    Position p = b.getPosition();
                    g.setForegroundColor(TextColor.ANSI.RED);
                    g.putString(p.getCol(), p.getRow(), "B");
                }
            }
            // render explosions
            for (Explosion ex : game.getExplosions()) {
                if (ex.isActive()) {
                    Position p = ex.getPosition();
                    g.setForegroundColor(TextColor.ANSI.YELLOW);
                    g.putString(p.getCol(), p.getRow(), "X");
                }
            }
            // render powerups
            for (PowerUp pu : game.getPowerUps()) {
                if (pu.isActive()) {
                    Position p = pu.getPosition();
                    g.setForegroundColor(TextColor.ANSI.GREEN);
                    g.putString(p.getCol(), p.getRow(), "P");
                }
            }
            // render enemies
            for (NormalEnemy e : game.getEnemies()) {
                if (e.isActive()) {
                    Position p = e.getPosition();
                    g.setForegroundColor(TextColor.ANSI.CYAN);
                    g.putString(p.getCol(), p.getRow(), "E");
                }
            }
            // render player
            Player player = game.getPlayer();
            if (player != null && player.isActive()) {
                Position p = player.getPosition();
                g.setForegroundColor(TextColor.ANSI.BLUE);
                g.putString(p.getCol(), p.getRow(), "@");
            }
            // hud
            int hudRow = rows + 1;
            g.setForegroundColor(TextColor.ANSI.WHITE);
            g.putString(0, hudRow, String.format("Lives: %d  Bombs: %d/%d  Score: %d  Time: %.0f",
                    player != null ? player.getHealth().getLives() : 0,
                    player != null ? player.getActiveBombs() : 0,
                    player != null ? player.getBombCapacity() : 0,
                    game.getScore(),
                    game.getTimer()));
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    private char tileToChar(int row, int col, Map map) {
        TileType tile = map.getTiles()[row][col];
        Block block = map.getBlockAt(new Position(row, col));
        if (block != null && block.isActive()) {
            return switch (block.getType()) {
                case INDESTRUCTIBLE -> '#';
                case DESTRUCTIBLE -> 'D';
                case REINFORCED -> 'R';
            };
        }
        if (tile == null) return ' ';
        return switch (tile) {
            case WALKABLE, EMPTY -> '.';
            case WATER -> 'W';
            case ICE -> 'I';
            case INDESTRUCTIBLE -> '#';
            case DESTRUCTIBLE -> 'D';
            case REINFORCED -> 'R';
            case SPAWN_PLAYER -> '.';
            case SPAWN_ENEMY -> '.';
            case SPAWN_BOSS -> '.';
            case EXIT -> 'X';
            default -> tile.tileToChar();
        };
    }

    @Override
    public void renderGameOver(GameState state) {
        try {
            screen.clear();
            TextGraphics g = screen.newTextGraphics();
            g.setForegroundColor(TextColor.ANSI.WHITE);
            g.putString(2, 2, "=== GAME OVER ===");
            g.putString(2, 4, state == GameState.WIN ? "YOU WIN!" : "YOU LOSE");
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    @Override
    public void renderInvalidChoice() {
        try {
            TextGraphics g = screen.newTextGraphics();
            g.setForegroundColor(TextColor.ANSI.RED);
            g.putString(0, 0, "Invalid choice");
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    @Override
    public void renderPaused() {
        try {
            TextGraphics g = screen.newTextGraphics();
            g.setForegroundColor(TextColor.ANSI.YELLOW);
            g.putString(0, 0, "PAUSED");
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    @Override
    public void renderGameOverOptions() {
        try {
            TextGraphics g = screen.newTextGraphics();
            g.setForegroundColor(TextColor.ANSI.WHITE);
            g.putString(2, 6, "R: restart, Q: quit");
            screen.refresh();
        } catch (Exception e) {
            // ignore
        }
    }

    @Override
    public void renderExitMessage() {
        try {
            screen.stopScreen();
            terminal.close();
        } catch (Exception e) {
            // ignore
        }
    }

}
