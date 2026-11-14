package nabla.model.powerup;

import nabla.model.entity.Entity;
import nabla.type.PowerUpType;
import nabla.util.Position;

/**
 * Power-Up -- item yang memberikan peningkatan kemampuan (F06).
 * <p>
 * Muncul setelah Destructible Block dihancurkan (30% peluang) atau ditempatkan pada lokasi tertentu.
 */
public class PowerUp extends Entity {
    private final PowerUpType type;

    public PowerUp(PowerUpType type, Position position) {
        super(position);
        this.type = type;
    }

    public PowerUpType getType() { return type; }

    @Override
    public void update(double deltaTime) {
        /* PowerUp tidak butuh update otomatis */
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }
}