package nabla;

import nabla.game.GameLoop;
import nabla.game.controller.CLIController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;
import nabla.ui.cli.ConsoleRenderer;

import java.util.Scanner;

/**
 * Main entry point Nangor Blast (F09.1).
 * <p>
 * <b>Prinsip</b>
 * <ul>
 *   <li><b>Facade Pattern</b>
 *   Kelas ini hanya menjadi orchestrator sehingga tidak punya logika game.
 *   </li>
 *   <li><b>Dependency Injection</b>
 *   Game, GameLoop, Renderer, Scanner (kalau pakai CLI), MapLoader di-inject ke CLIController.
 *   </li>
 * </ul>
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        IRenderer renderer = new ConsoleRenderer(game);
        /* TODO ini harus diganti karena Scanner hanya perlaku pada CLI */
        Scanner scanner = new Scanner(System.in);
        MapLoader mapLoader = new MapLoader();

        CLIController controller = new CLIController(game, gameLoop, renderer, scanner, mapLoader);
        controller.run();
    }
}
