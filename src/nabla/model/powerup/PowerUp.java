package nabla.model.powerup;

import nabla.model.entity.Collidable;
import nabla.model.entity.Entity;
import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Power-Up item yang memberikan peningkatan kemampuan (F06).
 * <p>
 * Muncul setelah Destructible Block dihancurkan (30% peluang) atau ditempatkan pada lokasi tertentu.
 * Efek diterapkan oleh power-up saat collide dengan Player.
 */
public abstract class PowerUp extends Entity {

    protected PowerUp(Position position) {
        super(position);
    }

    protected abstract void apply(Player player);

    @Override
    public void onCollide(Collidable other) {
        if (other instanceof Player player) {
            apply(player);
            deactivate();
        }
    }

    @Override
    public void update(double deltaTime) {
        /* PowerUp tidak butuh update otomatis */
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }
}
