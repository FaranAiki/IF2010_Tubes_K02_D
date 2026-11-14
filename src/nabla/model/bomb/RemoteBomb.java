package nabla.model.bomb;

import nabla.model.entity.Player;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Remote Bomb -- bom yang diledakkan secara manual (F02.2.3).
 * <p>
 * Hanya bisa digunakan setelah player memperoleh Remote Detonator.
 * Tidak meledak berdasarkan fuse time. Dipicu melalui perintah DETONATE (Q).
 * Memberikan 1 damage kepada boss yang rentan.
 */
public class RemoteBomb extends Bomb {
    public static final int BOSS_VULNERABLE_DMG = 1;

    public RemoteBomb(Player owner, Position position, int blastRange) {
        super(BombType.REMOTE, owner, position, Double.MAX_VALUE, blastRange);
    }

    @Override
    protected boolean hasFuse() {
        return false;
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