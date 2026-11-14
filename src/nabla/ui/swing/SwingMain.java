package nabla.ui.swing;

import nabla.game.GameLoop;
import nabla.game.controller.SwingController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;

/**
 * Entry point UI Swing. Dipanggil via {@code make run-swing}.
 * <p>
 * <ul>
 *   <li><b>Prinsip Facade Pattern</b> Kelas ini hanya menjadi orchestrator Swing.</li>
 *   <li><b>Prinsip Dependency Injection</b> Game, GameLoop, Renderer, MapLoader
 *   di-inject ke SwingController.</li>
 * </ul>
 */
public class SwingMain {
    public static void main(String[] args) {
        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        IRenderer renderer = new SwingRenderer(game);
        MapLoader mapLoader = new MapLoader();

        SwingController controller = new SwingController(game, gameLoop, renderer);
        controller.run();
    }
}
