package nabla.model.entity;

import nabla.model.component.BombInventory;
import nabla.model.component.HealthStats;
import nabla.model.strategy.IMovementStrategy;
import nabla.model.strategy.NormalMoveStrategy;
import nabla.type.BombType;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Player (Deeper) -- entitas yang dikendalikan pemain.
 *
 * <ul>
 *   <li><b>Prinsip Composition over Inheritance</b> Player <i>memiliki</i> komponen
 *       {@link HealthStats}, {@link BombInventory}, dan
 *       {@link IMovementStrategy}. Tidak ada field statis yang
 *       final/privat -- semua state dipisahkan ke komponen reusable.</li>
 *   <li><b>Prinsip Strategy Pattern</b> Pergerakan diatur oleh
 *       {@link IMovementStrategy}. Bisa diganti
 *       (Normal/IceSlide/WaterSlow) tanpa ubah method move().</li>
 *   <li><b>Prinsip Single Responsibility</b> Player hanya urus state pemain,
 *       bukan logika game loop atau input parsing.</li>
 * </ul>
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

    /**
     * Konstruktor Player.
     *
     * @param position posisi awal player
     */
    public Player(Position position) {
        super(position);
        this.health = new HealthStats(DEFAULT_LIVES, MAX_LIVES);
        this.bombInventory = new BombInventory(DEFAULT_BOMB_CAPACITY, BombType.NORMAL);
        this.movementStrategy = new NormalMoveStrategy();
    }

    /**
     * Mengatur strategi pergerakan player.
     *
     * @param strategy strategi pergerakan baru
     */
    public void setMovementStrategy(IMovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    /**
     * Gerak player ke arah tertentu.
     *
     * @param dir arah gerakan
     * @param canMove fungsi pengecekan apakah tile tujuan dapat dilalui
     * @return true jika player berhasil bergerak, false jika terhalang
     */
    public boolean move(Direction dir,
                        java.util.function.BiPredicate<Position, Direction> canMove) {
        this.direction = dir;
        Position newPos = movementStrategy.tryMove(position, dir, canMove);
        if (!newPos.isSameTile(position)) {
            position = newPos;
            return true;
        }
        return false;
    }

    /**
     * Coba pasang bom di tile player.
     *
     * @return true jika bom berhasil dipasang
     */
    public boolean placeBomb() {
        return bombInventory.tryPlace();
    }

    /**
     * Kembalikan kapasitas bom (setelah bom meledak).
     */
    public void releaseBombCapacity() {
        bombInventory.release();
    }

    /**
     * Terima damage. Player tidak menerima damage saat invulnerable.
     *
     * @param amount jumlah damage
     * @return true jika player mati setelah menerima damage
     */
    public boolean takeDamage() {
        if (invulnerable) return false;
        return health.takeDamage(1);
    }

    /**
     * Cek apakah player mati.
     *
     * @return true jika player mati
     */
    public boolean isDead() {
        return health.isDead();
    }

    /**
     * Tambah nyawa player.
     */
    public void addLife() {
        health.heal(1);
    }

    /**
     * Tambah kapasitas bom player.
     */
    public void addBombCapacity() {
        bombInventory.addCapacity(1, MAX_BOMB_CAPACITY);
    }

    /**
     * Tambah jangkauan ledakan player.
     */
    public void addBlastRange() {
        if (blastRange < MAX_BLAST_RANGE) blastRange++;
    }

    /**
     * Tambah kecepatan player.
     */
    public void addSpeed() {
        speed = Math.min(speed * 1.5, DEFAULT_SPEED * 2.0);
    }

    /**
     * Buka kemampuan Remote Bomb.
     */
    public void unlockRemote() {
        remoteUnlocked = true;
    }

    /**
     * Reset state player ke nilai awal.
     */
    public void reset() {
        health.reset();
        bombInventory.reset(DEFAULT_BOMB_CAPACITY, BombType.NORMAL);
        blastRange = DEFAULT_BLAST_RANGE;
        speed = DEFAULT_SPEED;
        invulnerable = false;
        remoteUnlocked = false;
        direction = Direction.UP;
    }

    /**
     * Mendapatkan arah hadap player.
     *
     * @return arah hadap
     */
    public Direction getDirection() { return direction; }

    /**
     * Mendapatkan komponen health player.
     *
     * @return HealthStats player
     */
    public HealthStats getHealth() { return health; }

    /**
     * Mendapatkan komponen inventory bom player.
     *
     * @return BombInventory player
     */
    public BombInventory getBombInventory() { return bombInventory; }

    /**
     * Mendapatkan kapasitas bom player.
     *
     * @return kapasitas bom
     */
    public int getBombCapacity() { return bombInventory.getCapacity(); }

    /**
     * Mendapatkan jumlah bom aktif.
     *
     * @return jumlah bom aktif
     */
    public int getActiveBombs() { return bombInventory.getActive(); }

    /**
     * Mendapatkan jangkauan ledakan player.
     *
     * @return jangkauan ledakan
     */
    public int getBlastRange() { return blastRange; }

    /**
     * Mendapatkan kecepatan player.
     *
     * @return kecepatan
     */
    public double getSpeed() { return speed; }

    /**
     * Cek apakah player dalam status invulnerable.
     *
     * @return true jika invulnerable
     */
    public boolean isInvulnerable() { return invulnerable; }

    /**
     * Cek apakah Remote Bomb telah dibuka.
     *
     * @return true jika Remote Bomb terbuka
     */
    public boolean isRemoteUnlocked() { return remoteUnlocked; }

    /**
     * Mendapatkan tipe bom yang dipilih.
     *
     * @return tipe bom terpilih
     */
    public BombType getSelectedBombType() { return bombInventory.getSelectedType(); }

    /**
     * Mengatur tipe bom yang dipilih.
     *
     * @param type tipe bom baru
     */
    public void setSelectedBombType(BombType type) { bombInventory.setSelectedType(type); }

    /**
     * Mengatur status invulnerable player.
     *
     * @param v status invulnerable baru
     */
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