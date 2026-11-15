package nabla.ui.swing;

import nabla.game.GameLoop;
import nabla.controller.IController;
import nabla.game.MapLoader;
import nabla.controller.SwingController;
import nabla.game.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;

/**
 * Concrete Factory untuk UI Swing.
 * <p>
 * <b>Design Pattern Abstract Factory Pattern</b> Membuat family produk Swing:
 * SwingRenderer dan SwingController.
 */
public class SwingFactory implements UIFactory {
    @Override
    public IRenderer createRenderer(Game game) {
        return new SwingRenderer(game);
    }

    @Override
    public IController createController(Game game, GameLoop gameLoop, IRenderer renderer, MapLoader mapLoader) {
        return new SwingController(game, gameLoop, renderer, mapLoader);
    }

    @Override
    public String getModeName() {
        return "swing";
    }
}