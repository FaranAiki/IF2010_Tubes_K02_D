package nabla.game;

import nabla.model.bomb.Bomb;
import nabla.model.bomb.Explosion;
import nabla.model.collision.CollisionChecker;
import nabla.model.entity.IEntity;
import nabla.model.entity.Player;
import nabla.model.enemy.BossEnemy;
import nabla.model.enemy.NormalEnemy;
import nabla.model.powerup.PowerUp;
import nabla.model.world.Map;
import nabla.type.EnemyBehavior;
import nabla.type.GameState;
import nabla.util.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Game state manager utama (F00).
 * <p>
 * Menyediakan satu interface untuk mengakses semua state permainan
 * (player, enemies, bombs, blocks). Hanya urus state dan tidak update loop (GameLoop yg ngurus).
 */
public class Game {
    private Player player;
    private Map currentMap;
    private BossEnemy boss;

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
     * Player dan enemies dibuat dari spawn positions di map.
     */
    public void initialize(Map map) {
        this.currentMap = map;
        this.player = new Player(map.getPlayerSpawn());
        this.enemies.clear();
        for (Position spawn: map.getEnemySpawns()) {
            this.enemies.add(new NormalEnemy(spawn, EnemyBehavior.PATROL, new ArrayList<>(), 1.0));
        }
        this.bombs.clear();
        this.explosions.clear();
        this.powerUps.clear();
        this.score = 0;
        this.timer = 300;
        this.gameState = GameState.PLAYING;
        this.collisionChecker = new CollisionChecker(map, this::getAllBlockers);
    }

    private List<IEntity> getAllBlockers() {
        List<IEntity> all = new ArrayList<>(enemies);
        if (boss != null && boss.isActive()) {
            all.add(boss);
        }
        return all;
    }

    public void restart() {
        if (currentMap != null) {
            initialize(currentMap);
        }
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public Player getPlayer() { return player; }
    public Map getCurrentMap() { return currentMap; }
    public BossEnemy getBoss() { return boss; }
    public void setBoss(BossEnemy boss) { this.boss = boss; }
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
