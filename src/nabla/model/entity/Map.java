package nabla.model.entity;

import nabla.exception.InvalidMapException;
import nabla.model.block.Block;
import nabla.model.block.DestructibleBlock;
import nabla.model.block.IndestructibleBlock;
import nabla.model.block.ReinforcedBlock;
import nabla.model.enemy.BossEnemy;
import nabla.model.enemy.NormalEnemy;
import nabla.type.BombType;
import nabla.type.EnemyBehavior;
import nabla.type.PowerUpType;
import nabla.type.TileType;
import nabla.util.Position;
import nabla.util.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Map -- area permainan berbentuk grid (F07).
 * <p>
 * <b>Prinsip:</b>
 * <ul>
 *   <li><b>Composition over Inheritance:</b> Map <i>memiliki</i> {@link Size}
 *       untuk dimensi, bukan static final fields.</li>
 *   <li><b>Factory Method:</b> Buat map dari string layout melalui
 *       {@link #fromLayout(String, String)}.</li>
 *   <li><b>Template Method:</b> {@link #validate()} adalah template yang
 *       diimplementasikan oleh subclass (map type).</li>
 * </ul>
 */
public class Map {
    private final Size size;
    private TileType[][] tiles;
    private Block[][] blocks;
    private Position playerSpawn;
    private final List<Position> enemySpawns = new ArrayList<>();
    private Position bossSpawn;
    private Position exit;
    private Player player;
    private final List<NormalEnemy> enemies = new ArrayList<>();
    private BossEnemy boss;

    public Map(int width, int height) {
        this.size = new Size(width, height);
        this.tiles = new TileType[height][width];
        this.blocks = new Block[height][width];
    }

    public Map(Size size) {
        this.size = size;
        this.tiles = new TileType[size.getHeight()][size.getWidth()];
        this.blocks = new Block[size.getHeight()][size.getWidth()];
    }

    /**
     * Buat Map dari string layout (F07.2 Legenda).
     * <p>
     * Contoh layout (Green Garden):
     * <pre>
     *  # # # # # # # # # # # # # #
     *  # P . . D . . . D . . . X #
     *  # . # . # # # . # . D . . #
     *  ...
     *  # # # # # # # # # # # # # #
     * </pre>
     */
    public static Map fromLayout(String name, String layout) {
        String[] lines = layout.trim().split("\n");
        int height = lines.length;
        /* Hitung lebar dari baris pertama */
        String firstLine = lines[0].replaceAll("\\s+", "");
        int width = firstLine.length();

        Map map = new Map(width, height);
        map.tiles = new TileType[height][width];
        map.blocks = new Block[height][width];

        for (int row = 0; row < height; row++) {
            String line = lines[row].replaceAll("\\s+", "");
            if (line.length() != width) {
                throw new InvalidMapException(name + ": baris " + (row + 1) + " harus " + width + " kolom");
            }
            for (int col = 0; col < width; col++) {
                char c = line.charAt(col);
                Position pos = new Position(row, col);
                TileType tile = charToTile(c, pos, map);
                map.tiles[row][col] = tile;
            }
        }

        /* Buat player & enemy berdasarkan spawn */
        if (map.playerSpawn != null) {
            map.player = new Player(map.playerSpawn);
        }
        for (Position spawn : map.enemySpawns) {
            /* Default: patrol behavior */
            map.enemies.add(new NormalEnemy(spawn, EnemyBehavior.PATROL, new ArrayList<>(), 1.0));
        }

        return map;
    }

    private static TileType charToTile(char c, Position pos, Map map) {
        switch (c) {
            case '#': map.blocks[pos.getRow()][pos.getCol()] = new IndestructibleBlock(pos);
                     return TileType.INDESTRUCTIBLE;
            case 'D': map.blocks[pos.getRow()][pos.getCol()] = new DestructibleBlock(pos);
                     return TileType.DESTRUCTIBLE;
            case 'R': map.blocks[pos.getRow()][pos.getCol()] = new ReinforcedBlock(pos);
                     return TileType.REINFORCED;
            case '.': return TileType.WALKABLE;
            case 'P': map.playerSpawn = pos; return TileType.SPAWN_PLAYER;
            case 'E': map.enemySpawns.add(pos); return TileType.SPAWN_ENEMY;
            case 'B': map.bossSpawn = pos; return TileType.SPAWN_BOSS;
            case 'X': map.exit = pos; return TileType.EXIT;
            case 'W': return TileType.WATER;
            case 'S': return TileType.SWITCH;
            case 'G': return TileType.GATE_CLOSED;
            case 'g': return TileType.GATE_OPEN;
            case 'H': return TileType.LAVA;
            case 'I': return TileType.ICE;
            case 'L': return TileType.LASER;
            default: return TileType.WALKABLE;
        }
    }

    /** Validasi map (F00.3). Override oleh subclass. */
    public void validate() {
        if (playerSpawn == null) throw new InvalidMapException("Tidak ada spawn player");
        if (enemySpawns.size() != 5) throw new InvalidMapException("Harus ada 5 spawn enemy, dapat " + enemySpawns.size());
        if (bossSpawn == null) throw new InvalidMapException("Tidak ada spawn boss");
        if (exit == null) throw new InvalidMapException("Tidak ada pintu keluar");
    }

    public Size getSize() { return size; }
    public int getWidth() { return size.getWidth(); }
    public int getHeight() { return size.getHeight(); }
    public TileType[][] getTiles() { return tiles; }
    public Block[][] getBlocks() { return blocks; }
    public Position getPlayerSpawn() { return playerSpawn; }
    public List<Position> getEnemySpawns() { return enemySpawns; }
    public Position getBossSpawn() { return bossSpawn; }
    public Position getExit() { return exit; }
    public Player getPlayer() { return player; }
    public List<NormalEnemy> getEnemies() { return enemies; }
    public BossEnemy getBoss() { return boss; }
    public void setBoss(BossEnemy boss) { this.boss = boss; }
}