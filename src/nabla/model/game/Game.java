package nabla.model.game;

import nabla.model.bomb.Bomb;
import nabla.model.bomb.Explosion;
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
 * <ul>
 *   <li><b>Design Pattern Facade Pattern</b> Game menyediakan satu interface untuk
 *       mengakses semua state permainan (player, enemies, bombs, blocks).</li>
 *   <li><b>Design Pattern Single Responsibility</b> Game hanya urus state sehingga tidak update loop.</li>
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
     * Mendapatkan objek player.
     *
     * @return player
     */
    public Player getPlayer() { return player; }

    /**
     * Mendapatkan map saat ini.
     *
     * @return map saat ini
     */
    public Map getCurrentMap() { return currentMap; }

    /**
     * Mendapatkan daftar enemy biasa.
     *
     * @return daftar NormalEnemy
     */
    public List<NormalEnemy> getEnemies() { return enemies; }

    /**
     * Mendapatkan daftar bom aktif.
     *
     * @return daftar Bomb
     */
    public List<Bomb> getBombs() { return bombs; }

    /**
     * Mendapatkan daftar ledakan aktif.
     *
     * @return daftar Explosion
     */
    public List<Explosion> getExplosions() { return explosions; }

    /**
     * Mendapatkan daftar power-up aktif.
     *
     * @return daftar PowerUp
     */
    public List<PowerUp> getPowerUps() { return powerUps; }

    /**
     * Mendapatkan skor saat ini.
     *
     * @return skor
     */
    public int getScore() { return score; }

    /**
     * Mengatur skor.
     *
     * @param s skor baru
     */
    public void setScore(int s) { this.score = s; }

    /**
     * Mendapatkan sisa waktu (detik).
     *
     * @return waktu tersisa
     */
    public int getTimer() { return timer; }

    /**
     * Mengatur sisa waktu.
     *
     * @param t waktu baru dalam detik
     */
    public void setTimer(int t) { this.timer = t; }

    /**
     * Mendapatkan state game saat ini.
     *
     * @return GameState
     */
    public GameState getGameState() { return gameState; }

    /**
     * Mengatur state game.
     *
     * @param s state baru
     */
    public void setGameState(GameState s) { this.gameState = s; }

    /**
     * Cek apakah permainan sedang berjalan.
     *
     * @return true jika state PLAYING
     */
    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }
}
