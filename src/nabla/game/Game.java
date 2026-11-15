package nabla.game;

import nabla.model.bomb.Bomb;
import nabla.model.bomb.BombFactory;
import nabla.model.bomb.Explosion;
import nabla.model.collision.CollisionChecker;
import nabla.model.Entity;
import nabla.model.Player;
import nabla.type.BombType;
import nabla.model.enemy.BossEnemy;
import nabla.model.enemy.NormalEnemy;
import nabla.model.powerup.PowerUp;
import nabla.model.world.Map;
import nabla.singleton.Config;
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
    private double timer;
    private GameState gameState = GameState.MENU;
    private CollisionChecker collisionChecker;

    public Game() {
    }

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
        this.timer = Config.DEFAULT_TIMER_SECONDS;
        this.gameState = GameState.PLAYING;
        this.collisionChecker = new CollisionChecker(map, this::getAllCollidables);
    }

    private List<Entity> getAllCollidables() {
        List<Entity> all = new ArrayList<>();
        if (player != null) all.add(player);
        all.addAll(enemies);
        if (boss != null) all.add(boss);
        all.addAll(bombs);
        all.addAll(explosions);
        all.addAll(powerUps);
        if (currentMap != null) {
            all.addAll(currentMap.getBlocks());
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
    public double getTimer() { return timer; }
    public void setTimer(double t) { this.timer = t; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState s) { this.gameState = s; }

    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }

    public boolean placeBomb() {
        if (player == null || currentMap == null) return false;
        Position pos = player.getPosition();
        for (Bomb b: bombs) {
            if (b.isActive() && b.getPosition().isSameTile(pos)) return false;
        }
        if (!player.placeBomb()) return false;
        Bomb bomb = BombFactory.create(player.getSelectedBombType(), player, new Position(pos.getRow(), pos.getCol()), player.getBlastRange());
        bombs.add(bomb);
        return true;
    }

    public void detonateRemoteBombs() {
        if (player == null) return;
        for (Bomb b: bombs) {
            if (b.isActive() && b.getOwner() == player && b.getType() == BombType.REMOTE) {
                b.trigger();
            }
        }
    }
}
