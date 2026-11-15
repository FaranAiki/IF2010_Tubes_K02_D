package nabla.model.powerup;

import nabla.model.collision.ICollidable;
import nabla.model.Entity;
import nabla.model.Player;
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
    public void onCollide(Entity other) {
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
