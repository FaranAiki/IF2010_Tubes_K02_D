package nabla.ui;

import nabla.controller.IController;
import nabla.game.Game;

/**
 * Abstract Factory untuk menciptakan family produk UI yang terkait.
 * <p>
 * Setiap concrete factory (CLI, Swing, JavaFX) menciptakan
 * Renderer dan Controller yang cocok untuk platform tersebut.
 * <p>
 * <b>Design Pattern Abstract Factory Pattern</b> Memisahkan pembuatan
 * family objek terkait tanpa menspesifikasikan kelas konkret.
 * <p>
 */
public interface UIFactory {
    IRenderer createRenderer(Game game);

    IController createController(Game game);

    String getModeName();
}
