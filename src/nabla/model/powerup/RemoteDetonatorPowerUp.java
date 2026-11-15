package nabla.model.powerup;

import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Power-Up yang membuka kemampuan Remote Bomb.
 */
public class RemoteDetonatorPowerUp extends PowerUp {

    public RemoteDetonatorPowerUp(Position position) {
        super(position);
    }

    @Override
    protected void apply(Player player) {
        player.unlockRemote();
    }
}
