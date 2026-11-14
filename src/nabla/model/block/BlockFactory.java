package nabla.model.block;

import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Factory Method Pattern -- factory untuk membuat block.
 * <p>
 * <b>Prinsip:</b> Open/Closed. Tambah block baru cukup di sini.
 */
public class BlockFactory {
    public static Block create(BlockType type, Position position) {
        switch (type) {
            case INDESTRUCTIBLE:
                return new IndestructibleBlock(position);
            case DESTRUCTIBLE:
                return new DestructibleBlock(position);
            case REINFORCED:
                return new ReinforcedBlock(position);
            default:
                throw new IllegalArgumentException("Unknown BlockType: " + type);
        }
    }
}