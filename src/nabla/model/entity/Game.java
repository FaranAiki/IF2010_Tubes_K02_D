package nabla.model.entity;

import nabla.model.bomb.Bomb;
import nabla.model.bomb.Explosion;
import nabla.model.enemy.NormalEnemy;
import nabla.model.powerup.PowerUp;
import nabla.type.GameState;
import java.util.ArrayList;
import java.util.List;

/**
 * Game -- state manager utama (F00).
 * <p>
 * <b>Prinsip:</b>
 * <ul>
 *   <li><b>Facade Pattern:</b> Game menyediakan satu interface untuk
 *       mengakses semua state permainan (player, enemies, bombs, blocks).</li>
 *   <li><b>Single Responsibility:</b> Game hanya urus state, bukan update loop.</li>
 * </ul>
 */
public class Game {
    private Player player;
    private Map currentMap;
    private List<NormalEnemy> enemies = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private List<PowerUp> powerUps = new ArrayList<>();
    private int score = 0;
    private int timer = 300; /* detik */
    private GameState gameState = GameState.MENU;

    public Game() {
    }

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
    }

    public void restart() {
        if (currentMap != null) {
            initialize(currentMap);
        }
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