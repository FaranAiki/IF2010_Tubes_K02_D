package nabla.ui.cli;

import nabla.ui.AbstractRenderer;
import nabla.game.Game;
import nabla.model.world.Map;
import nabla.type.GameState;
import nabla.type.TileType;

/**
 * Console Renderer -- tampilan terminal (CLI).
 * <p>
 * <ul>
 *   <li><b>Design Pattern Single Responsibility</b>
 *   Hanya urus rendering, bukan logika game.
 *   </li>
 *   <li><b>Design Pattern Open/Closed</b>
 *   Bisa di-extend untuk JavaFX (View layer).
 *   </li>
 * </ul>
 */
public class ConsoleRenderer extends AbstractRenderer {

    public ConsoleRenderer(Game game) {
        super(game);
    }

    @Override
    public void renderMenu() {

    }

    @Override
    public void renderHowToPlay() {

    }

    @Override
    public void renderGame(Game game) {

    }

    private char tileToChar(int row, int col, TileType[][] tiles, Map map) {
        return ' ';
    }

    @Override
    public void renderGameOver(GameState state) {

    }

    @Override
    public void renderInvalidChoice() {

    }

    @Override
    public void renderPaused() {

    }

    @Override
    public void renderGameOverOptions() {

    }

    @Override
    public void renderExitMessage() {

    }
}