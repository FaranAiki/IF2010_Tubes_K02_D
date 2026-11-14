package nabla.model.block;

import nabla.type.BombType;
import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Destructible Block -- bisa dihancurkan oleh Explosion (F04.2.2).
 * <p>
 * Bisa menyimpan Power-Up yang muncul setelah block dihancurkan (30% peluang).
 */
public class DestructibleBlock extends Block {
    public DestructibleBlock(Position position) {
        super(BlockType.DESTRUCTIBLE, position);
    }

    @Override
    public boolean isDestructibleByExplosion() {
        return true;
    }

    @Override
    public boolean isDestructibleByBombType(BombType bombType) {
        /* Semua bom bisa menghancurkannya melalui Explosion */
        return true;
    }

    @Override
    public void destroy() {
        deactivate();
    }

    @Override
    public boolean stopsExplosion() {
        /* Explosion berhenti pada block ini (F03.2.8) */
        return true;
    }
}