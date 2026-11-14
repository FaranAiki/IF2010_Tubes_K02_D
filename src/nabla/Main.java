package nabla;

import nabla.game.GameLoop;
import nabla.game.controller.IController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;
import nabla.ui.UIModeFactory;

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
 * <b>Prinsip Facade Pattern</b> (orchestrator tanpa logika game) &
 * Dependency Injection (Game, GameLoop, Renderer, MapLoader di-inject ke controller).
 * <p>
 * <b>Menggunakan Abstract Factory Pattern</b> via UIModeFactory untuk
 * menciptakan family produk UI (Renderer + Controller) per platform.
 */
public class Main {
    public static void main(String[] args) {
        /* Pengecekan args dan jika tidak ada argument, default ke javafx  */
        String ui = args.length > 0 ? args[0].trim().toLowerCase() : "javafx";

        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        MapLoader mapLoader = new MapLoader();

        UIFactory factory = UIModeFactory.getFactory(ui);
        if (factory == null) {
            System.err.println("Mode UI tidak dikenal: " + ui + ". Gunakan: cli, swing, javafx");
            System.exit(1);
        }

        IRenderer renderer = factory.createRenderer(game);
        Scanner scanner = "cli".equals(ui) ? new Scanner(System.in) : null;
        IController controller = factory.createController(game, gameLoop, renderer, scanner, mapLoader);
        controller.run();
    }
}
