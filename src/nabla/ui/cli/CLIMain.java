package nabla.ui.cli;

import nabla.game.GameLoop;
import nabla.game.controller.CLIController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;

import java.util.Scanner;

/**
 * Entry point UI CLI (Console). Dipanggil via {@code make run-cli}.
 * <p>
 * <ul>
 *   <li><b>Prinsip Facade Pattern</b> Kelas ini hanya menjadi orchestrator CLI.</li>
 *   <li><b>Prinsip Dependency Injection</b> Game, GameLoop, Renderer, Scanner,
 *   MapLoader di-inject ke CLIController.</li>
 * </ul>
 */
public class CLIMain {
    public static void main(String[] args) {
        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        IRenderer renderer = new ConsoleRenderer(game);
        Scanner scanner = new Scanner(System.in);
        MapLoader mapLoader = new MapLoader();

        CLIController controller = new CLIController(game, gameLoop, renderer, scanner, mapLoader);
        controller.run();
    }
}
