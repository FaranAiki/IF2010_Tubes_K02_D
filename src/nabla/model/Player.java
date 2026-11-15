package nabla.model;

import nabla.model.component.BombInventory;
import nabla.model.component.HealthStats;
import nabla.model.collision.CollisionChecker;
import nabla.model.collision.Collidable;
import nabla.model.bomb.Explosion;
import nabla.model.enemy.BossEnemy;
import nabla.model.enemy.NormalEnemy;
import nabla.type.BombType;
import nabla.util.Direction;
import nabla.util.Position;
import nabla.model.bomb.ICanUseBomb;
import nabla.singleton.Config;

import java.util.List;

/**
 * Player (Deeper) entitas yang dikendalikan pemain.
 * <p>
 * Menggunakan Composition: memiliki HealthStats, BombInventory, dan IMovementStrategy.
 * Pergerakan diatur oleh Strategy Pattern (Normal/IceSlide/WaterSlow) tanpa ubah move().
 */
public class Player extends Entity implements ICanUseBomb {
    private Direction direction = null;
    private int blastRange = Config.DEFAULT_BLAST_RANGE;
    private double speed = Config.DEFAULT_SPEED;
    private boolean invulnerable = false;
    private double invulnerableTime = 0;
    private boolean remoteUnlocked = false;

    private final HealthStats health;
    private final BombInventory bombInventory;

    public Player(Position position) {
        super(position);
        this.health = new HealthStats(Config.DEFAULT_LIVES, Config.MAX_LIVES);
        this.bombInventory = new BombInventory(Config.DEFAULT_BOMB_CAPACITY, BombType.NORMAL);
    }

    /**
     * Gerak player ke arah tertentu.
     * <p>
     * Alur: Controller -> Player -> CollisionChecker -> balik ke Player.
     * Player nanya checker apakah bisa bergerak, jika bisa update posisi.
     */
    public boolean move(Direction dir, CollisionChecker checker) {
        this.direction = dir;
        if (checker.canMove(position, dir, this)) {
            position = dir.deltaPosition().add(position);
            if (checker != null) {
                checker.resolveCollisions(this, position);
            }
            return true;
        }
        return false;
    }

    public boolean placeBomb() {
        return bombInventory.tryPlace();
    }

    public void releaseBombCapacity() {
        bombInventory.release();
    }

    public boolean takeDamage() {
        if (invulnerable) return false;
        invulnerable = true;
        invulnerableTime = Config.HIT_INVULNERABLE_TIME;
        return health.takeDamage(1);
    }

    public boolean isDead() {
        return health.isDead();
    }

    public void addLife() {
        health.heal(1);
    }

    public void addBombCapacity() {
        bombInventory.addCapacity(1, Config.MAX_BOMB_CAPACITY);
    }

    public void addBlastRange() {
        if (blastRange < Config.MAX_BLAST_RANGE) blastRange++;
    }

    public void addSpeed() {
        speed = Math.min(speed * 1.5, Config.DEFAULT_SPEED * 2.0);
    }

    public void unlockRemote() {
        remoteUnlocked = true;
    }

    public void reset() {
        health.reset();
        bombInventory.reset(Config.DEFAULT_BOMB_CAPACITY, BombType.NORMAL);
        blastRange = Config.DEFAULT_BLAST_RANGE;
        speed = Config.DEFAULT_SPEED;
        invulnerable = false;
        invulnerableTime = 0;
        remoteUnlocked = false;
        direction = Direction.UP;
    }

    public Direction getDirection() { return direction; }
    public HealthStats getHealth() { return health; }
    public BombInventory getBombInventory() { return bombInventory; }
    public int getBombCapacity() { return bombInventory.getCapacity(); }
    public int getActiveBombs() { return bombInventory.getActive(); }
    public int getBlastRange() { return blastRange; }
    public double getSpeed() { return speed; }
    public boolean isInvulnerable() { return invulnerable; }
    public boolean isRemoteUnlocked() { return remoteUnlocked; }
    public BombType getSelectedBombType() { return bombInventory.getSelectedType(); }
    public void setSelectedBombType(BombType type) { bombInventory.setSelectedType(type); }
    public void setInvulnerable(boolean v) { this.invulnerable = v; }

    @Override
    public void onCollide(Collidable other) {
        /* Single Dispatch: TODO pertimbangkan apakah ini lebih baik or no */
        if (other instanceof NormalEnemy || other instanceof BossEnemy) {
            if (!invulnerable) {
                takeDamage();
            }
        } else if (other instanceof Explosion explosion) {
            takeDamage();
        }
    }

    @Override
    public void update(double deltaTime) {
        if (invulnerableTime > 0) {
            invulnerableTime -= deltaTime;
            if (invulnerableTime <= 0) {
                invulnerable = false;
            }
        }
    }

    @Override
    public void applyExplosion(int damage, List<Position> affectedTiles) {
        if (invulnerable || affectedTiles == null) return;
        for (Position tile : affectedTiles) {
            if (position.isSameTile(tile)) {
                takeDamage();
                return;
            }
        }
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }
}
