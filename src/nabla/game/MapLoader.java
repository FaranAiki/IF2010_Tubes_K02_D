package nabla.game;

import nabla.game.Game;
import nabla.model.world.Map;
import nabla.singleton.Config;

/**
 * Map Loader memuat map dari layout string.
 * <p>
 * <b>Design Pattern Single Responsibility</b> dengan memisahkan loading map dari map itu sendiri
 * Karena MapLoader berhubungan langsung dengan game, sementara Map tidak harus berhubungan
 * dengan Game
 * Bisa di-extend untuk load dari file, resource, dll.
 */
public class MapLoader {
    public void loadDefaultMap(Game game) {
        Map map = Map.fromLayout(Config.defaultMapName, Config.defaultMapLayout);
        map.validate();
        game.initialize(map);
    }

    public void loadMap(Game game, String name, String layout) {
        Map map = Map.fromLayout(name, layout);
        map.validate();
        game.initialize(map);
    }
}
