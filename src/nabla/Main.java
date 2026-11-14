package nabla;

import nabla.game.GameLoop;
import nabla.game.MapLoader;
import nabla.game.controller.IController;
import nabla.model.game.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;
import nabla.ui.UIModeFactory;

import java.util.Scanner;

/**
 * Main entry point Nangor Blast (F09.1).
 * <p>
 * Dipilih UI melalui argumen baris perintah: nabla.Main (default JavaFX),
 * nabla.Main cli (CLI), nabla.Main swing (Swing), nabla.Main javafx (JavaFX).
 */
public class Main {
    public static void main(String[] args) {
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