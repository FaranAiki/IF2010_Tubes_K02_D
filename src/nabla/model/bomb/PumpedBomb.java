package nabla.model.bomb;

import nabla.model.entity.Player;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Pumped Bomb -- bom dengan daya ledak lebih besar (F02.2.2).
 *
 * Meledak 4,5 detik. Jangkauan +2 dari Normal Bomb (max 7).
 * Dapat menghancurkan Reinforced Block. Memberikan 2 damage kepada boss yang rentan.
 */
public class PumpedBomb extends Bomb {
    public static final double FUSE_TIME = 4.5;
    public static final int RANGE_BONUS = 2;
    public static final int MAX_RANGE = 7;
    public static final int BOSS_VULNERABLE_DMG = 2;

    public PumpedBomb(Player owner, Position position, int blastRange) {
        super(BombType.PUMPED, owner, position, FUSE_TIME, Math.min(blastRange + RANGE_BONUS, MAX_RANGE));
    }

    @Override
    protected void onDetonate() {
        /* Explosion dibuat oleh GameLoop melalui createExplosion() */
    }

    @Override
    public Explosion createExplosion() {
        return new Explosion(position, blastRange, owner, 0.5);
    }
}