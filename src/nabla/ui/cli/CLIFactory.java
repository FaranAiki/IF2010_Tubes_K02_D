package nabla.ui.cli;

import nabla.game.GameLoop;
import nabla.controller.CLIController;
import nabla.controller.IController;
import nabla.game.MapLoader;
import nabla.game.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;

/**
 * Concrete Factory untuk UI CLI (Console).
 * <p>
 * <b>Design Pattern Abstract Factory Pattern</b> Membuat family produk CLI:
 * ConsoleRenderer dan CLIController.
 */
public class CLIFactory implements UIFactory {
    @Override
    public IRenderer createRenderer(Game game) {
        return new ConsoleRenderer(game);
    }

    @Override
    public IController createController(Game game, GameLoop gameLoop, IRenderer renderer, MapLoader mapLoader) {
        return new CLIController(game, gameLoop, renderer, mapLoader);
    }

    @Override
    public String getModeName() {
        return "cli";
    }
}