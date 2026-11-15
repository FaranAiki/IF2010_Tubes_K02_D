package nabla;

import nabla.game.GameLoop;
import nabla.game.MapLoader;
import nabla.game.controller.IController;
import nabla.game.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;
import nabla.ui.UIModeFactory;

import java.util.Scanner;

/**
 * Main entry point Nangor Blast (F09.1).
 * <p>
 * Dipilih UI melalui argumen baris perintah args pada command line
 * nabla.Main (default JavaFX),
 * atau nabla.Main cli (CLI),
 *      nabla.Main swing (Swing),
 *      nabla.Main javafx (JavaFX).
 */
public class Main {
    public static void main(String[] args) {
        String ui = args.length > 0 ? args[0].trim().toLowerCase() : "javafx";

        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);
        MapLoader mapLoader = new MapLoader();

        /* Abstract factory di sini */
        UIFactory factory = UIModeFactory.getFactory(ui);
        if (factory == null) {
            System.exit(1);
        }

        IRenderer renderer = factory.createRenderer(game);
        /* TODO keknya scanner kita pisahin aja */
        Scanner scanner = "cli".equals(ui) ? new Scanner(System.in) : null;
        IController controller = factory.createController(game, gameLoop, renderer, scanner, mapLoader);
        controller.run();
    }
}
