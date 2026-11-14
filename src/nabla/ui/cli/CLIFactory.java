package nabla.ui.cli;

import nabla.game.GameLoop;
import nabla.game.controller.CLIController;
import nabla.game.controller.IController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;

import java.util.Scanner;

/**
 * Concrete Factory untuk UI CLI (Console).
 * <p>
 * <b>Prinsip Abstract Factory Pattern</b> Membuat family produk CLI:
 * ConsoleRenderer dan CLIController.
 */
public class CLIFactory implements UIFactory {
    @Override
    public IRenderer createRenderer(Game game) {
        return new ConsoleRenderer(game);
    }

    @Override
    public IController createController(Game game, GameLoop gameLoop, IRenderer renderer, Scanner scanner, MapLoader mapLoader) {
        return new CLIController(game, gameLoop, renderer, scanner, mapLoader);
    }

    @Override
    public String getModeName() {
        return "cli";
    }
}