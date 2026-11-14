package nabla.model.bomb;

import nabla.model.entity.Player;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Factory Method Pattern -- factory untuk membuat bom.
 * <p>
 * <b>Prinsip:</b> Open/Closed. Jika kita ingin tambah bom baru (mis. Ice Bomb),
 * kita hanya buat class baru + tambah case di factory. Code yang memanggil
 * factory tidak perlu diubah.
 */
public class BombFactory {
    public static Bomb create(BombType type, Player owner, Position position, int blastRange) {
        switch (type) {
            case NORMAL:
                return new NormalBomb(owner, position, blastRange);
            case PUMPED:
                return new PumpedBomb(owner, position, blastRange);
            case REMOTE:
                return new RemoteBomb(owner, position, blastRange);
            default:
                throw new IllegalArgumentException("Unknown BombType: " + type);
        }
    }
}