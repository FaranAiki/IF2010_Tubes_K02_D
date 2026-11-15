package nabla.model.powerup;

import nabla.model.Player;
import nabla.util.Position;

/**
 * Power-Up yang menambah nyawa.
 */
public class HeartPowerUp extends PowerUp {

    public HeartPowerUp(Position position) {
        super(position);
    }

    @Override
    protected void apply(Player player) {
        player.addLife();
    }
}
