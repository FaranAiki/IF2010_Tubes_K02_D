package nabla.ui.javafx;

import nabla.game.GameLoop;
import nabla.game.controller.JavaFXController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;

/**
 * Entry point UI JavaFX. Dipanggil via {@code make run-javafx} atau
 * {@code make run} (default).
 * <p>
 * <ul>
 *   <li><b>Prinsip Facade Pattern</b> Kelas ini hanya menjadi orchestrator JavaFX.</li>
 *   <li><b>Prinsip Dependency Injection</b> Game, GameLoop, Renderer, MapLoader
 *   di-inject ke JavaFXController.</li>
 * </ul>
 */
public class JavaFXMain {
    public static void main(String[] args) {
        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        IRenderer renderer = new JavaFXRenderer(game);
        MapLoader mapLoader = new MapLoader();

        JavaFXController controller = new JavaFXController(game, gameLoop, renderer);
        controller.run();
    }
}
