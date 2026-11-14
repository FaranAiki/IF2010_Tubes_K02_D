package nabla.model.block;

import nabla.type.BombType;
import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Reinforced Block -- hanya bisa dihancurkan oleh Pumped Bomb (F04.2.3).
 * <p>
 * Digunakan pada Green Garden. Normal Bomb dan Remote Bomb tidak bisa menghancurkannya.
 */
public class ReinforcedBlock extends Block {
    public ReinforcedBlock(Position position) {
        super(BlockType.REINFORCED, position);
    }

    @Override
    public boolean isDestructibleByExplosion() {
        /* Hancurkan hanya jika ledakan dari Pumped Bomb */
        return false;
    }

    @Override
    public boolean isDestructibleByBombType(BombType bombType) {
        return bombType == BombType.PUMPED;
    }

    @Override
    public void destroy() {
        deactivate();
    }

    @Override
    public boolean stopsExplosion() {
        /* Menghentikan penyebaran Explosion (F03.2.10) */
        return true;
    }
}