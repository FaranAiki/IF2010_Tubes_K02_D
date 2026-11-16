package nabla.ui.cli;

import nabla.controller.CLIController;
import nabla.controller.IController;
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
    public IController createController(Game game) {
        return new CLIController(game);
    }

    @Override
    public String getModeName() {
        return "cli";
    }
}