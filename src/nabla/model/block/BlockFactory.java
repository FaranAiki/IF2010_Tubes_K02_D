package nabla.model.block;

import nabla.type.BlockType;
import nabla.util.Position;

/**
 * Factory Method Pattern -- factory untuk membuat block.
 * <p>
 * <b>Prinsip Open/Closed</b>. Tambah block baru cukup di sini.
 */
public class BlockFactory {
    /**
     * Buat block sesuai tipe.
     *
     * @param type tipe block yang ingin dibuat
     * @param position posisi block pada grid
     * @return instance Block sesuai tipe
     * @throws IllegalArgumentException jika tipe block tidak dikenal
     */
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