package nabla.model.block;

import nabla.type.BombType;
import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Indestructible Block -- tidak bisa dihancurkan (F04.2.1).
 * <p>
 * Menghalangi pergerakan, menghentikan penyebaran Explosion,
 * tidak bisa ditempati bomb.
 */
public class IndestructibleBlock extends Block {
    public IndestructibleBlock(Position position) {
        super(BlockType.INDESTRUCTIBLE, position);
    }

    @Override
    public boolean isDestructibleByExplosion() {
        return false;
    }

    @Override
    public boolean isDestructibleByBombType(BombType bombType) {
        return false;
    }

    @Override
    public void destroy() {
        /* Tidak bisa dihancurkan */
    }

    @Override
    public boolean stopsExplosion() {
        return true;
    }
}