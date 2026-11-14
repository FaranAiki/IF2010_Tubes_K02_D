package nabla.model.block;

import nabla.model.entity.Entity;
import nabla.model.powerup.PowerUp;
import nabla.type.BombType;
import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Block -- objek map yang menghalangi pergerakan dan penyebaran Explosion (F04).
 * <p>
 * <b>Prinsip:</b> <ul>
 *   <li><b>Factory Method:</b> Buat block melalui {@link BlockFactory},
 *       bukan langsung `new`.</li>
 *   <li><b>Strategy Pattern:</b> Perilaku "dihancurkan" dan
 *       "menghentikan ledakan" diatur oleh strategy, bukan if-else.</li>
 * </ul>
 */
public abstract class Block extends Entity {
    protected final BlockType type;
    protected PowerUp containedPowerUp;

    protected Block(BlockType type, Position position) {
        super(position);
        this.type = type;
    }

    /** Apakah block ini bisa dihancurkan oleh ledakan? */
    public abstract boolean isDestructibleByExplosion();

    /** Apakah block ini bisa dihancurkan oleh bom tertentu? */
    public abstract boolean isDestructibleByBombType(BombType bombType);

    /** Hancurkan block. */
    public abstract void destroy();

    /** Apakah block menghentikan penyebaran Explosion? */
    public abstract boolean stopsExplosion();

    /** Apakah block menghalangi pergerakan? */
    @Override
    public boolean blocksMovement() {
        return isActive();
    }

    /** Simpan Power-Up di dalam block (F04.2.2.3). */
    public void setContainedPowerUp(PowerUp powerUp) {
        this.containedPowerUp = powerUp;
    }

    public PowerUp getContainedPowerUp() { return containedPowerUp; }
    public BlockType getType() { return type; }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    @Override
    public void update(double deltaTime) {
        /* Block tidak butuh update otomatis */
    }
}