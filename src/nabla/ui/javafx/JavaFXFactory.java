package nabla.ui.javafx;

import nabla.controller.IController;
import nabla.controller.JavaFXController;
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
    public IController createController(Game game) {
        return new JavaFXController(game);
    }

    @Override
    public String getModeName() {
        return "javafx";
    }
}