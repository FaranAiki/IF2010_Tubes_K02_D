package nabla.model.bomb;

import nabla.model.Player;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Normal Bomb adalah bom dasar (F02.2.1).
 * <p>
 * Meledak 3 detik setelah dipasang. Jangkauan mengikuti blastRange player.
 */
public class NormalBomb extends Bomb {
    public NormalBomb(Player owner, Position position, int blastRange) {
        super(BombType.NORMAL, owner, position, 3.0, blastRange);
    }

    @Override
    protected void onDetonate() {
        /* Explosion dibuat oleh GameLoop melalui createExplosion() */
    }
}
