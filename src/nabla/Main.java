package nabla;

import nabla.game.GameLoop;
import nabla.game.Game;
import nabla.controller.IController;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;
import nabla.ui.UIModeFactory;

import nabla.singleton.Config;

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

        /* Abstract factory di sini */
        UIFactory factory = UIModeFactory.getFactory(ui);
        if (factory == null) {
            System.exit(1);
        }

        IRenderer renderer = factory.createRenderer(game);
        IController controller = factory.createController(game);

        /* GameLoop, Renderer, Controller masing-masing punya thread sendiri. */
        gameLoop.start();
        renderer.start();
        controller.start();

        /* Tunggu controller selesai (blocking main thread). */
        while (controller.isRunning()) {
            try {
                Thread.sleep(Config.SLEEPING_THREAD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        /* Shutdown semuanya. */
        renderer.stop();
        gameLoop.stop();
    }
}
