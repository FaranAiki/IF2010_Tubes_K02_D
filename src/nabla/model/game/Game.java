package nabla.model.game;

import nabla.model.bomb.Bomb;
import nabla.model.bomb.Explosion;
import nabla.model.collision.CollisionChecker;
import nabla.model.entity.Player;
import nabla.model.enemy.NormalEnemy;
import nabla.model.powerup.PowerUp;
import nabla.model.world.Map;
import nabla.type.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 * Game -- state manager utama (F00).
 * <p>
 * Menyediakan satu interface untuk mengakses semua state permainan
 * (player, enemies, bombs, blocks). Hanya urus state, tidak update loop.
 */
public class Game {
    private Player player;
    private Map currentMap;
    private List<NormalEnemy> enemies = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private int score = 0;
    private int timer = 300;
    private GameState gameState = GameState.MENU;
    private CollisionChecker collisionChecker;

    public Game() {
    }

    /**
     * Inisialisasi game dengan map yang diberikan.
     *
     * @param map map yang akan digunakan
     */
    public void initialize(Map map) {
        this.currentMap = map;
        this.player = map.getPlayer();
        this.enemies = new ArrayList<>(map.getEnemies());
        this.bombs.clear();
        this.explosions.clear();
        this.powerUps.clear();
        this.score = 0;
        this.timer = 300;
        this.gameState = GameState.PLAYING;
        this.collisionChecker = new CollisionChecker(map, new ArrayList<>());
    }

    /**
     * Restart permainan dengan map saat ini.
     */
    public void restart() {
        if (currentMap != null) {
            initialize(currentMap);
        }
    }

    /**
     * Mendapatkan collision checker untuk validasi gerakan.
     *
     * @return CollisionChecker, atau null jika game belum diinisialisasi
     */
    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public Player getPlayer() { return player; }
    public Map getCurrentMap() { return currentMap; }
    public List<NormalEnemy> getEnemies() { return enemies; }
    public List<Bomb> getBombs() { return bombs; }
    public List<Explosion> getExplosions() { return explosions; }
    public List<PowerUp> getPowerUps() { return powerUps; }
    public int getScore() { return score; }
    public void setScore(int s) { this.score = s; }
    public int getTimer() { return timer; }
    public void setTimer(int t) { this.timer = t; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState s) { this.gameState = s; }

    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }
}
