package nabla.game;

import nabla.model.game.Game;
import nabla.model.world.Map;

/**
 * Map Loader memuat map dari layout string.
 * <p>
 * <b>Design Pattern Single Responsibility</b> dengan memisahkan loading map dari map itu sendiri
 * Karena MapLoader berhubungan langsung dengan game, sementara Map tidak harus berhubungan
 * dengan Game
 * Bisa di-extend untuk load dari file, resource, dll.
 */
public class MapLoader {
    /** Layout default map (Kelompok D: Green Garden). */
    /* TODO ganti pakai config/map.conf loader
     * jadi nanti pakai file reader gitu */
    private static final String DEFAULT_LAYOUT =
        "# # # # # # # # # # # # # #\n" +
        "# P . . D . . . D . . . X #\n" +
        "# . # . # # # . # . D . . #\n" +
        "# . # . . . # . . E # # . #\n" +
        "# D . . R . . . R . . . . #\n" +
        "# # # . # # # . # # # E . #\n" +
        "# . E . D . . . # . . . . #\n" +
        "# . # # # . # . . . B . . #\n" +
        "# E . . . . D . . . . . E #\n" +
        "# # # # # # # # # # # # # #";

    /** Load default map (Kelompok D: Green Garden). */
    public void loadDefaultMap(Game game) {
        Map map = Map.fromLayout("Green Garden", DEFAULT_LAYOUT);
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
