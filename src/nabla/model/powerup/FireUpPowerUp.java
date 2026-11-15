package nabla.model.powerup;

import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Power-Up yang menambah jangkauan ledakan.
 */
public class FireUpPowerUp extends PowerUp {

    public FireUpPowerUp(Position position) {
        super(position);
    }

    @Override
    protected void apply(Player player) {
        player.addBlastRange();
    }
}
