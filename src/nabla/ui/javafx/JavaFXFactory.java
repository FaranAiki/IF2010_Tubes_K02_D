package nabla.ui.javafx;

import nabla.game.GameLoop;
import nabla.game.controller.IController;
import nabla.game.controller.JavaFXController;
import nabla.game.MapLoader;
import nabla.game.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;

/**
 * Concrete Factory untuk UI JavaFX.
 * <p>
 * <b>Design Pattern Abstract Factory Pattern</b> Membuat family produk JavaFX:
 * JavaFXRenderer dan JavaFXController.
 */
public class JavaFXFactory implements UIFactory {
    @Override
    public IRenderer createRenderer(Game game) {
        return new JavaFXRenderer(game);
    }

    @Override
    public IController createController(Game game, GameLoop gameLoop, IRenderer renderer, MapLoader mapLoader) {
        return new JavaFXController(game, gameLoop, renderer, mapLoader);
    }

    @Override
    public String getModeName() {
        return "javafx";
    }
}