package nabla.model.powerup;

import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Power-Up yang menambah kecepatan gerak.
 */
public class SpeedUpPowerUp extends PowerUp {

    public SpeedUpPowerUp(Position position) {
        super(position);
    }

    @Override
    protected void apply(Player player) {
        player.addSpeed();
    }
}
