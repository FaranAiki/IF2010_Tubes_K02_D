package nabla.model.collision;

import nabla.model.world.Map;
import nabla.model.entity.IEntity;
import nabla.type.TileType;
import nabla.util.Direction;
import nabla.util.Position;

import java.util.List;
import java.util.function.Supplier;

/**
 * Collision checker untuk mengecek apakah suatu posisi dapat dilalui.
 * <p>
 * Menyediakan aturan umum untuk block penghalang, batas map, dan entity lain
 * yang menghalangi pergerakan.
 */
public class CollisionChecker {
    private final Map map;
    private final Supplier<List<IEntity>> blockersSupplier;

    public CollisionChecker(Map map, Supplier<List<IEntity>> blockersSupplier) {
        this.map = map;
        this.blockersSupplier = blockersSupplier;
    }

    public boolean canMove(Position current, Direction direction) {
        Position target = direction.deltaPosition().add(current);

        if (target.getRow() < 0 || target.getRow() >= map.getHeight()
                || target.getCol() < 0 || target.getCol() >= map.getWidth()) {
            return false;
        }

        if (map.getBlocks()[target.getRow()][target.getCol()] != null) {
            return false;
        }

        List<IEntity> blockers = blockersSupplier.get();
        for (IEntity entity : blockers) {
            if (entity.isActive() && entity.blocksMovement() && entity.getPosition().isSameTile(target)) {
                return false;
            }
        }

        return true;
    }

    public TileType getTileAt(Position pos) {
        if (pos.getRow() < 0 || pos.getRow() >= map.getHeight()
                || pos.getCol() < 0 || pos.getCol() >= map.getWidth()) {
            return null;
        }
        return map.getTiles()[pos.getRow()][pos.getCol()];
    }
}
