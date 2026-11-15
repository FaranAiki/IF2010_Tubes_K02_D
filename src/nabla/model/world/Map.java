package nabla.model.world;

import nabla.exception.InvalidMapException;
import nabla.model.block.Block;
import nabla.model.block.BlockFactory;
import nabla.type.BlockType;
import nabla.type.TileType;
import nabla.util.Position;
import nabla.util.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Map representasi area permainan berbentuk grid (F07).
 * <p>
 * <ul>
 *   <li><b>Prinsip Composition over Inheritance</b> Map <i>memiliki</i> {@link Size}
 *       untuk dimensi, bukan static final agar bisa mudah diubah meskipun tidak harus.</li>
 *   <li><b>Prinsip Factory Method</b> Buat map dari string layout melalui
 *       {@link #fromLayout(String, String)}.</li>
 *   <li><b>Prinsip Template Method</b> {@link #validate()} adalah template yang
 *       diimplementasikan oleh subclass (map type).</li>
 * </ul>
 */
public class Map {
    /* Map tidak bisa berubah-ubah sehingga digunakan final */
    private final Size size;

    /* Array asli dari tiles */
    private TileType[][] tiles;

    /* Daftar block entity yang ada di map */
    private final List<Block> blocks = new ArrayList<>();
    private Position playerSpawn;
    private final List<Position> enemySpawns = new ArrayList<>();
    private Position bossSpawn;
    private Position exit;

    public Map(int width, int height) {
        this.size = new Size(width, height);
        this.tiles = new TileType[height][width];
    }

    public Map(Size size) {
        this.size = size;
        this.tiles = new TileType[size.getHeight()][size.getWidth()];
    }

    /**
     * Buat Map dari string layout (F07.2 Legenda).
     * Design Pattern Factory Method
     * Digunakan untuk mengubah suatu String menjadi objek Map
     * TODO: make sure ini digunakan untuk mengubah config
     */
    public static Map fromLayout(String name, String layout) {
        /* Konversi jadi array 1D dengan tiap entri string */
        String[] lines = layout.trim().split("\n");
        int height = lines.length;

        /* Hitung lebar dari baris pertama dengan menghilangkan spacebar/space lainnya */
        String firstLine = lines[0].replaceAll("\\s+", "");
        int width = firstLine.length();

        Map map = new Map(width, height);
        map.tiles = new TileType[height][width];

        for (int row = 0; row < height; row++) {
            String line = lines[row].replaceAll("\\s+", "");

            if (line.length() != width) {
                throw new InvalidMapException(name + ": baris " + (row + 1) + " harus " + width + " kolom");
            }

            for (int col = 0; col < width; col++) {
                char c = line.charAt(col);
                Position pos = new Position(row, col);
                TileType tile = TileType.charToTile(c);
                map.tiles[row][col] = tile;
                separate(c, pos, map, tile);
            }
        }

        /* Player dan enemy dibuat oleh Game, bukan Map */
        return map;
    }

    private static void separate(char c, Position pos, Map map, TileType tile) {
        switch (tile) {
            case INDESTRUCTIBLE:
                map.blocks.add(BlockFactory.create(BlockType.INDESTRUCTIBLE, pos));
                break;
            case DESTRUCTIBLE:
                map.blocks.add(BlockFactory.create(BlockType.DESTRUCTIBLE, pos));
                break;
            case REINFORCED:
                map.blocks.add(BlockFactory.create(BlockType.REINFORCED, pos));
                break;
            case SPAWN_PLAYER:
                map.playerSpawn = pos;
                break;
            case SPAWN_ENEMY:
                map.enemySpawns.add(pos);
                break;
            case SPAWN_BOSS:
                map.bossSpawn = pos;
                break;
            case EXIT:
                map.exit = pos;
                break;
            default:
                break;
        }
    }

    /**
     * Validasi map (F00.3). Override oleh subclass.
     */
    public void validate() {
        if (playerSpawn == null) throw new InvalidMapException("Tidak ada spawn player");
        /* TODO jangan hardcode 5 */
        if (enemySpawns.size() != 5) throw new InvalidMapException("Harus ada 5 spawn enemy, dapat " + enemySpawns.size());
        if (bossSpawn == null) throw new InvalidMapException("Tidak ada spawn boss");
        if (exit == null) throw new InvalidMapException("Tidak ada pintu keluar");
    }

    public Size getSize() { return size; }

    public int getWidth() { return size.getWidth(); }
    public int getHeight() { return size.getHeight(); }

    public TileType[][] getTiles() { return tiles; }
    public List<Block> getBlocks() { return blocks; }

    public Block getBlockAt(Position pos) {
        for (Block block : blocks) {
            if (block.isActive() && block.getPosition().isSameTile(pos)) {
                return block;
            }
        }
        return null;
    }

    public void removeBlock(Block block) {
        if (block != null) {
            block.deactivate();
            blocks.remove(block);
        }
    }

    public Position getPlayerSpawn() { return playerSpawn; }
    public List<Position> getEnemySpawns() { return enemySpawns; }

    public Position getBossSpawn() { return bossSpawn; }
    public Position getExit() { return exit; }
}
