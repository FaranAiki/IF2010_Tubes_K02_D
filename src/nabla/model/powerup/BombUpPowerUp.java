package nabla.model.powerup;

import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Power-Up yang menambah kapasitas bom.
 */
public class BombUpPowerUp extends PowerUp {

    public BombUpPowerUp(Position position) {
        super(position);
    }

    @Override
    protected void apply(Player player) {
        player.addBombCapacity();
    }
}
