package nabla.game.controller;

import nabla.model.entity.Game;
import nabla.model.entity.Map;

/**
 * Map Loader -- memuat map dari layout string.
 * <p>
 * <b>Prinsip:</b> Single Responsibility dengan memisahkan loading map dari controller.
 * Bisa di-extend untuk load dari file, resource, dll.
 */
public class MapLoader {
    /** Load default map (Green Garden). */
    public void loadDefaultMap(Game game) {
        String layout = """
                # # # # # # # # # # # # # #
                # P . . D . . . D . . . X #
                # . # . # # # . # . D . . #
                # . # . . . # . . E # # . #
                # D . . R . . . R . . . . #
                # # # . # # # . # # # E . #
                # . E . D . . . # . . . . #
                # . # # # . # . . . B . . #
                # E . . . . D . . . . . E #
                # # # # # # # # # # # # # #
                """;
        Map map = Map.fromLayout("Green Garden", layout);
        map.validate();
        game.initialize(map);
    }

    /** Load map dari layout string custom. */
    public void loadMap(Game game, String name, String layout) {
        Map map = Map.fromLayout(name, layout);
        map.validate();
        game.initialize(map);
    }
}
