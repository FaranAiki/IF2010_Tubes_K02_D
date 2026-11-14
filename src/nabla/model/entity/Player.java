package nabla.model.entity;

import nabla.model.component.BombInventory;
import nabla.model.component.HealthStats;
import nabla.model.collision.CollisionChecker;
import nabla.model.strategy.IMovementStrategy;
import nabla.model.strategy.NormalMoveStrategy;
import nabla.type.BombType;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Player (Deeper) entitas yang dikendalikan pemain.
 * <p>
 * Menggunakan Composition: memiliki HealthStats, BombInventory, dan IMovementStrategy.
 * Pergerakan diatur oleh Strategy Pattern (Normal/IceSlide/WaterSlow) tanpa ubah move().
 */
public class Player extends Entity {

    public static final int DEFAULT_LIVES = 3;
    public static final int MAX_LIVES = 5;
    public static final int DEFAULT_BOMB_CAPACITY = 1;
    public static final int MAX_BOMB_CAPACITY = 3;
    public static final int DEFAULT_BLAST_RANGE = 1;
    public static final int MAX_BLAST_RANGE = 5;
    public static final double DEFAULT_SPEED = 1.0;
    public static final double RESPAWN_INVULNERABLE_TIME = 1.5;

    private Direction direction = Direction.UP;
    private int blastRange = DEFAULT_BLAST_RANGE;
    private double speed = DEFAULT_SPEED;
    private boolean invulnerable = false;
    private boolean remoteUnlocked = false;

    private final HealthStats health;
    private final BombInventory bombInventory;
    private IMovementStrategy movementStrategy;

    public Player(Position position) {
        super(position);
        this.health = new HealthStats(DEFAULT_LIVES, MAX_LIVES);
        this.bombInventory = new BombInventory(DEFAULT_BOMB_CAPACITY, BombType.NORMAL);
        this.movementStrategy = new NormalMoveStrategy();
    }

    public void setMovementStrategy(IMovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    /**
     * Gerak player ke arah tertentu.
     * <p>
     * Collision detection didelegasikan ke CollisionChecker yang diberikan oleh Game.
     * Player tidak perlu tahu tentang aturan collision karena strategy dan checker yang memutuskan.
     */
    public boolean move(Direction dir, CollisionChecker checker) {
        this.direction = dir;
        Position newPos = movementStrategy.tryMove(position, dir, checker);
        if (!newPos.isSameTile(position)) {
            position = newPos;
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
        return health.takeDamage(1);
    }

    public boolean isDead() {
        return health.isDead();
    }

    public void addLife() {
        health.heal(1);
    }

    public void addBombCapacity() {
        bombInventory.addCapacity(1, MAX_BOMB_CAPACITY);
    }

    public void addBlastRange() {
        if (blastRange < MAX_BLAST_RANGE) blastRange++;
    }

    public void addSpeed() {
        speed = Math.min(speed * 1.5, DEFAULT_SPEED * 2.0);
    }

    public void unlockRemote() {
        remoteUnlocked = true;
    }

    public void reset() {
        health.reset();
        bombInventory.reset(DEFAULT_BOMB_CAPACITY, BombType.NORMAL);
        blastRange = DEFAULT_BLAST_RANGE;
        speed = DEFAULT_SPEED;
        invulnerable = false;
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
    public void update(double deltaTime) {
        /* Player tidak butuh update otomatis, gerakan dihandle oleh input */
    }

    @Override
    public void applyExplosion(int damage, Position center, int range) {
        if (invulnerable) return;
        if (position.distanceTo(center) <= range) {
            health.takeDamage(damage);
        }
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }
}
