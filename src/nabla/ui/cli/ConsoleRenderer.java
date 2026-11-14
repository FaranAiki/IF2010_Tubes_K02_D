package nabla.ui.cli;

import nabla.ui.IRenderer;
import nabla.model.game.Game;
import nabla.model.entity.IEntity;
import nabla.model.entity.Player;
import nabla.model.world.Map;
import nabla.type.GameState;
import nabla.type.TileType;
import nabla.util.Position;

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
public class ConsoleRenderer implements IRenderer {
    private final Game game;

    /**
     * Konstruktor ConsoleRenderer.
     *
     * @param game objek Game yang akan dirender
     */
    public ConsoleRenderer(Game game) {
        this.game = game;
    }

    /**
     * Render pilihan menu utama (F09.1.1).
     */
    @Override
    public void renderMenu() {

    }

    /**
     * Render petunjuk permainan (F09.1.2).
     */
    @Override
    public void renderHowToPlay() {

    }

    /**
     * Render state permainan (F09.3.5).
     *
     * @param game objek Game yang berisi state terkini
     */
    @Override
    public void renderGame(Game game) {

    }

    private char tileToChar(int row, int col, TileType[][] tiles, Map map) {
        return ' ';
    }

    /**
     * Render state WIN/LOSE (F09.4).
     *
     * @param state state permainan akhir (WIN atau LOSE)
     */
    @Override
    public void renderGameOver(GameState state) {

    }

    /**
     * Render pesan pilihan tidak valid.
     */
    @Override
    public void renderInvalidChoice() {

    }

    /**
     * Render layar pause.
     */
    @Override
    public void renderPaused() {

    }

    /**
     * Render opsi setelah game over.
     */
    @Override
    public void renderGameOverOptions() {

    }

    /**
     * Render pesan keluar.
     */
    @Override
    public void renderExitMessage() {

    }
}
