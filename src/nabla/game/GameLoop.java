package nabla.game;


import nabla.game.Game;
import nabla.model.bomb.Bomb;
import nabla.model.bomb.Explosion;
import nabla.model.block.Block;
import nabla.model.Entity;
import nabla.model.Player;
import nabla.model.enemy.NormalEnemy;
import nabla.singleton.Config;
import nabla.util.Position;
import nabla.model.collision.CollisionChecker;
import nabla.model.world.Map;

import java.util.ArrayList;
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
                /* fiks kalau game lemot biar tetep sesuai sama ekspektasi */
                if (game.isPlaying()) {
                    update(FIXED_DT);
                }
                accumulator -= FIXED_DT;
            }

            try {
                Thread.sleep(Config.FAST_SLEEPING_THREAD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void update(double dt) {
        game.setTimer(Math.max(0, game.getTimer() - dt));
        if (game.getTimer() <= 0) {
            game.setGameState(nabla.type.GameState.LOSE);
            return;
        }

        CollisionChecker checker = game.getCollisionChecker();
        Player player = game.getPlayer();
        if (player != null) {
            player.update(dt);
        }
        for (NormalEnemy enemy: game.getEnemies()) {
            enemy.updateEnemy(dt, player, checker);
        }
        if (game.getBoss() != null) {
            game.getBoss().update(dt);
        }

        List<Bomb> detonated = new ArrayList<>();
        for (Bomb bomb: game.getBombs()) {
            bomb.update(dt);
            if (!bomb.isActive()) {
                detonated.add(bomb);
            }
        }
        for (Bomb bomb: detonated) {
            spawnExplosion(bomb);
            if (bomb.getOwner() != null) {
                bomb.getOwner().releaseBombCapacity();
            }
        }
        game.getBombs().removeIf(b -> !b.isActive());

        for (Explosion explosion: game.getExplosions()) {
            explosion.update(dt);
            if (!explosion.isApplied()) {
                applyExplosion(explosion);
                explosion.setApplied(true);
            }
        }
        game.getExplosions().removeIf(e -> !e.isActive());
        game.getEnemies().removeIf(e -> !e.isActive());

        if (game.getBoss() != null && !game.getBoss().isActive()) {
            game.getBoss().deactivate();
        }

        if (checker != null && player != null && player.isActive()) {
            checker.resolveCollisions(player, player.getPosition());
        }
        game.getPowerUps().removeIf(p -> !p.isActive());
        checkGameEnd();
    }

    private void spawnExplosion(Bomb bomb) {
        game.getExplosions().add(bomb.createExplosion());
    }

    private void applyExplosion(Explosion explosion) {
        Position center = explosion.getPosition();
        int range = explosion.getBlastRange();
        List<Position> affected = collectExplosionTiles(center, range);
        int damage = explosion.getDamage();

        for (NormalEnemy enemy: game.getEnemies()) {
            if (!enemy.isActive()) {
                game.getEnemies().remove(enemy);
            } else {
                enemy.applyExplosion(damage, affected);
            }
        }
        if (game.getBoss() != null && game.getBoss().isActive()) {
            game.getBoss().applyExplosion(damage, affected);
        }
        for (Bomb bomb: game.getBombs()) {
            for (Position p : affected) {
                if (bomb.isActive() && bomb.getPosition().isSameTile(p)) {
                    bomb.trigger();
                }
            }
        }

         Map map = game.getCurrentMap();
         if (game.getPlayer() != null && game.getPlayer().isActive()) {
             game.getPlayer().applyExplosion(damage, affected);
         }
         for (Position p: affected) {
            Block block = map.getBlockAt(p);
            if (block != null && block.isDestructibleByExplosion()) {
                block.destroy();
                map.removeBlock(block);
            }
        }
    }

    private List<Position> collectExplosionTiles(Position center, int range) {
        List<Position> tiles = new ArrayList<>();
        tiles.add(center);
        nabla.util.Direction[] dirs = nabla.util.Direction.values();
        Map map = game.getCurrentMap();
        for (nabla.util.Direction dir : dirs) {
            for (int i = 1; i <= range; i++) {
                Position p = dir.deltaPosition();
                Position target = new Position(center.getRow() + p.getRow() * i, center.getCol() + p.getCol() * i);
                if (target.getRow() < 0 || target.getRow() >= map.getHeight()
                        || target.getCol() < 0 || target.getCol() >= map.getWidth()) {
                    break;
                }
                Block block = map.getBlockAt(target);
                if (block != null) {
                    if (block.stopsExplosion()) {
                        tiles.add(target);
                    }
                    break;
                }
                tiles.add(target);
            }
        }
        return tiles;
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
        // TODO tambahin Thread input line dan Renderer
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
