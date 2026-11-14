package nabla.ui;

import nabla.game.GameLoop;
import nabla.game.controller.IController;
import nabla.game.MapLoader;
import nabla.model.game.Game;

import java.util.Scanner;

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
    /**
     * Membuat renderer untuk platform UI ini.
     *
     * @param game objek Game yang akan dirender
     * @return IRenderer implementation
     */
    IRenderer createRenderer(Game game);

    /**
     * Membuat controller untuk platform UI ini.
     *
     * @param game      objek Game
     * @param gameLoop  GameLoop instance
     * @param renderer  Renderer yang sudah dibuat
     * @param scanner   Scanner untuk input (CLI), bisa null untuk GUI
     * @param mapLoader MapLoader instance
     * @return IController implementation
     */
    IController createController(Game game, GameLoop gameLoop, IRenderer renderer, Scanner scanner, MapLoader mapLoader);

    /**
     * Mendapatkan nama mode UI (untuk logging/debug).
     *
     * @return nama mode UI
     */
    String getModeName();
}