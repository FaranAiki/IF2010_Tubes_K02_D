package nabla.ui;

import nabla.game.GameLoop;
import nabla.controller.IController;
import nabla.game.MapLoader;
import nabla.game.Game;

/**
 * Abstract Factory untuk menciptakan family produk UI yang terkait.
 * <p>
 * Setiap concrete factory (CLI, Swing, JavaFX) menciptakan
 * Renderer dan Controller yang cocok untuk platform tersebut.
 * <p>
 * <b>Design Pattern Abstract Factory Pattern</b> Memisahkan pembuatan
 * family objek terkait tanpa menspesifikasikan kelas konkret.
 */
public interface UIFactory {
    IRenderer createRenderer(Game game);

    IController createController(Game game, GameLoop gameLoop, IRenderer renderer, MapLoader mapLoader);

    String getModeName();
}
