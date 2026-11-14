package nabla;

import nabla.game.GameLoop;
import nabla.game.controller.CLIController;
import nabla.game.controller.JavaFXController;
import nabla.game.controller.MapLoader;
import nabla.game.controller.SwingController;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;
import nabla.ui.cli.ConsoleRenderer;
import nabla.ui.javafx.JavaFXRenderer;
import nabla.ui.swing.SwingRenderer;

import java.util.Scanner;

/**
 * Main entry point Nangor Blast (F09.1).
 * <p>
 * Dipilih UI melalui argumen baris perintah:
 * <ul>
 *   <li>{@code nabla.Main} atau {@code nabla.Main javafx} -> JavaFX (default)</li>
 *   <li>{@code nabla.Main cli} -> CLI (ConsoleRenderer)</li>
 *   <li>{@code nabla.Main swing} -> Swing</li>
 * </ul>
 * <p>
 * Prinsip Facade Pattern (orchestrator tanpa logika game) &
 * Dependency Injection (Game, GameLoop, Renderer, MapLoader di-inject ke controller).
 */
public class Main {
    public static void main(String[] args) {
        /* Pengecekan args dan jika tidak ada argument, default ke javafx  */
        String ui = args.length > 0 ? args[0].toLowerCase() : "javafx";

        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        MapLoader mapLoader = new MapLoader();

        switch (ui) {
            case "cli" -> {
                IRenderer renderer = new ConsoleRenderer(game);
                Scanner scanner = new Scanner(System.in);
                CLIController controller = new CLIController(game, gameLoop, renderer, scanner, mapLoader);
                controller.run();
            }
            case "swing" -> {
                IRenderer renderer = new SwingRenderer(game);
                SwingController controller = new SwingController(game, gameLoop, renderer);
                controller.run();
            }
            /* Jika user asal, default ke JavaFX */
            default -> {
                IRenderer renderer = new JavaFXRenderer(game);
                JavaFXController controller = new JavaFXController(game, gameLoop, renderer);
                controller.run();
            }
        }
    }
}
