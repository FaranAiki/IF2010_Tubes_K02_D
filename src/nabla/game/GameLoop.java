package nabla.game;

import nabla.model.entity.Game;
import nabla.model.entity.IEntity;
import java.util.List;

/**
 * Game Loop -- proses utama yang berjalan selama permainan berlangsung (F09.3).
 * <p>
 * <b>Prinsip</b>
 * <ul>
 *   <li><b>Concurrency</b>
 *       Berjalan di thread terpisah, update state secara
 *       periodik (fixed timestep). Thread ini adalah "game thread".
 *  </li>
 *   <li><b>Single Responsibility</b>
 *       Hanya urus update state, bukan input
 *       atau rendering.
 *   </li>
 * </ul>
 */
public class GameLoop implements Runnable {
    private final Game game;
    private volatile boolean running = false;
    private Thread thread;
    private double deltaTime = 0.0;
    private static final double TARGET_DT = 1.0 / 60.0; /* 60 FPS */

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        while (running) {
            long now = System.nanoTime();
            double dt = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (game.isPlaying()) {
                update(dt);
            }
            try {
                Thread.sleep(1); /* Hemat CPU */
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /* Game loop utama terjadi di sini per deltaTime untuk mengupdate semua kemungkinan entitas */
    private void update(double dt) {
        /* 1. Update timer */
        game.setTimer(game.getTimer() - (int) dt);
        if (game.getTimer() <= 0) {
            game.setGameState(nabla.type.GameState.LOSE);
            return;
        }

        /* 2. Update entities */
        for (IEntity entity : game.getEnemies()) {
            entity.update(dt);
        }
        for (IEntity entity : game.getBombs()) {
            entity.update(dt);
        }
        for (IEntity entity : game.getExplosions()) {
            entity.update(dt);
        }

        /* 3. Update boss */
        if (game.getCurrentMap() != null && game.getCurrentMap().getBoss() != null) {
            game.getCurrentMap().getBoss().update(dt);
        }

        /* 4. Hapus entity tidak aktif */
        game.getEnemies().removeIf(e -> !e.isActive());
        game.getBombs().removeIf(e -> !e.isActive());
        game.getExplosions().removeIf(e -> !e.isActive());

        /* 5. Cek kondisi menang atau kalah */
        checkGameEnd();
    }

    private void checkGameEnd() {
        /* Jika memang, semua enemy dan boss mati dan player di exit */
        boolean allEnemiesDead = game.getEnemies().isEmpty()
                && (game.getCurrentMap() == null || game.getCurrentMap().getBoss() == null
                    || !game.getCurrentMap().getBoss().isActive());
        if (allEnemiesDead && game.getPlayer().getPosition().isSameTile(game.getCurrentMap().getExit())) {
            game.setGameState(nabla.type.GameState.WIN);
            return;
        }

        /* Jika kalah, player mati */
        if (game.getPlayer().isDead()) {
            game.setGameState(nabla.type.GameState.LOSE);
        }
    }

    public void start() {
        if (running) return;
        running = true;
        thread = new Thread(this, "GameLoop");
        thread.start();
    }

    public void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean isRunning() { return running; }
    public double getDelta() { return deltaTime; }
}
