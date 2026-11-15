package nabla.game;

import nabla.game.Game;
import nabla.model.entity.IEntity;
import nabla.singleton.Config;

import java.util.List;

/**
 * Game Loop sebagai proses utama yang berjalan selama permainan berlangsung (F09.3).
 * <p>
 * <ul>
 *   <li><b>Design Pattern Concurrency</b>
 *       Berjalan di thread terpisah dan update state secara
 *       periodik (fixed timestep). Thread ini adalah "game thread".
 *  </li>
 *   <li><b>Design Pattern Single Responsibility</b>
 *       Hanya urus update state, bukan input
 *       atau rendering dan juga bukan Game itu sendiri.
 *   </li>
 * </ul>
 */
public class GameLoop implements Runnable {
    private final Game game;
    private volatile boolean running = false;
    private Thread thread;
    private double accumulator = 0.0;
    private static final double FIXED_DT = Config.TARGET_DT;

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

            accumulator += dt;

            while (accumulator >= FIXED_DT) {
                if (game.isPlaying()) {
                    update(FIXED_DT);
                }
                accumulator -= FIXED_DT;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void update(double dt) {
        game.setTimer(game.getTimer() - (int) dt);
        if (game.getTimer() <= 0) {
            game.setGameState(nabla.type.GameState.LOSE);
            return;
        }

        for (IEntity entity: game.getEnemies()) {
            entity.update(dt);
        }
        for (IEntity entity: game.getBombs()) {
            entity.update(dt);
        }
        for (IEntity entity: game.getExplosions()) {
            entity.update(dt);
        }

        if (game.getBoss() != null) {
            game.getBoss().update(dt);
        }

        /* Hapus semua yg sudah tidak aktif */
        game.getEnemies().removeIf(e -> !e.isActive());
        game.getBombs().removeIf(e -> !e.isActive());
        game.getExplosions().removeIf(e -> !e.isActive());

        checkGameEnd();
    }

    private void checkGameEnd() {
        boolean allEnemiesDead = game.getEnemies().isEmpty()
                && (game.getBoss() == null || !game.getBoss().isActive());
        if (allEnemiesDead && game.getPlayer().getPosition().isSameTile(game.getCurrentMap().getExit())) {
            game.setGameState(nabla.type.GameState.WIN);
            return;
        }

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
    public double getDelta() { return FIXED_DT; }
}
