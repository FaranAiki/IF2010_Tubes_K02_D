package nabla.ui.javafx;

import nabla.game.GameLoop;
import nabla.game.controller.IController;
import nabla.game.controller.JavaFXController;
import nabla.game.controller.MapLoader;
import nabla.model.entity.Game;
import nabla.ui.IRenderer;
import nabla.ui.UIFactory;

/**
 * Concrete Factory untuk UI JavaFX.
 * <p>
 * <b>Prinsip Abstract Factory Pattern</b> Membuat family produk JavaFX:
 * JavaFXRenderer dan JavaFXController.
 */
public class JavaFXFactory implements UIFactory {
    @Override
    public IRenderer createRenderer(Game game) {
        return new JavaFXRenderer(game);
    }

    @Override
    public IController createController(Game game, GameLoop gameLoop, IRenderer renderer, java.util.Scanner scanner, MapLoader mapLoader) {
        return new JavaFXController(game, gameLoop, renderer, mapLoader);
    }

    @Override
    public String getModeName() {
        return "javafx";
    }
}