package nabla.model.block;

import nabla.model.entity.Entity;
import nabla.model.powerup.PowerUp;
import nabla.type.BombType;
import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Block -- objek map yang menghalangi pergerakan dan penyebaran Explosion (F04).
 * <p>
 * <ul>
 *   <li><b>Prinsip Factory Method</b> Buat block melalui {@link BlockFactory}, bukan langsung {@code new}.</li>
 *   <li><b>Prinsip Strategy Pattern</b> Perilaku "dihancurkan" dan "menghentikan ledakan" diatur oleh
 *       strategy, bukan if-else.</li>
 * </ul>
 */
public abstract class Block extends Entity {
    protected final BlockType type;
    protected PowerUp containedPowerUp;

    /**
     * Konstruktor Block.
     *
     * @param type tipe block
     * @param position posisi block pada grid
     */
    protected Block(BlockType type, Position position) {
        super(position);
        this.type = type;
    }

    /**
     * Cek apakah block ini bisa dihancurkan oleh ledakan.
     *
     * @return true jika dapat dihancurkan oleh ledakan
     */
    public abstract boolean isDestructibleByExplosion();

    /**
     * Cek apakah block ini bisa dihancurkan oleh bom tertentu.
     *
     * @param bombType tipe bom yang digunakan
     * @return true jika dapat dihancurkan oleh bombType
     */
    public abstract boolean isDestructibleByBombType(BombType bombType);

    /**
     * Hancurkan block.
     */
    public abstract void destroy();

    /**
     * Cek apakah block menghentikan penyebaran Explosion.
     *
     * @return true jika menghentikan ledakan
     */
    public abstract boolean stopsExplosion();

    /**
     * Cek apakah block menghalangi pergerakan.
     *
     * @return true jika block aktif dan menghalangi gerakan
     */
    @Override
    public boolean blocksMovement() {
        return isActive();
    }

    /**
     * Simpan Power-Up di dalam block (F04.2.2.3).
     *
     * @param powerUp power-up yang disimpan
     */
    public void setContainedPowerUp(PowerUp powerUp) {
        this.containedPowerUp = powerUp;
    }

    /**
     * Mendapatkan Power-Up yang tersimpan di dalam block.
     *
     * @return power-up yang tersimpan, atau null jika kosong
     */
    public PowerUp getContainedPowerUp() { return containedPowerUp; }

    /**
     * Mendapatkan tipe block.
     *
     * @return tipe block
     */
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