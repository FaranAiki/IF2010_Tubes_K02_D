package nabla.model.collision;

import nabla.model.world.Map;
import nabla.model.entity.IEntity;
import nabla.model.powerup.PowerUp;
import nabla.type.TileType;
import nabla.util.Direction;
import nabla.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Collision checker untuk mengecek apakah suatu posisi dapat dilalui dan entitas yang bertabrakan.
 * <p>
 * Menyediakan aturan umum untuk block penghalang, batas map, dan entity lain
 * yang menghalangi pergerakan atau memicu respons collision.
 */
public class CollisionChecker {
    private final Map map;
    /* Game yang menentukan siapa saja entitas collidable
     * sehingga CollisionChecker nurut aja sama game */
    private final Supplier<List<IEntity>> collidablesSupplier;

    public CollisionChecker(Map map, Supplier<List<IEntity>> collidablesSupplier) {
        this.map = map;
        this.collidablesSupplier = collidablesSupplier;
    }

    public boolean canMove(Position current, Direction direction) {
        return canMove(current, direction, null);
    }

    public boolean canMove(Position current, Direction direction, IEntity mover) {
        Position target = direction.deltaPosition().add(current);

        if (target.getRow() < 0 || target.getRow() >= map.getHeight()
                || target.getCol() < 0 || target.getCol() >= map.getWidth()) {
            return false;
        }

        for (IEntity entity : collidablesSupplier.get()) {
            if (entity == mover) continue;
            if (entity.isActive() && entity.blocksMovement() && entity.getPosition() != null
                    && entity.getPosition().isSameTile(target)) {
                return false;
            }
        }

        return true;
    }

    public List<IEntity> getCollisionsAt(Position pos, IEntity mover) {
        List<IEntity> collisions = new ArrayList<>();
        for (IEntity entity : collidablesSupplier.get()) {
            if (entity == null || entity == mover) continue;
            if (entity.isActive() && entity.getPosition() != null && entity.getPosition().isSameTile(pos)) {
                collisions.add(entity);
            }
        }
        return collisions;
    }

    public void resolveCollisions(IEntity mover, Position pos) {
        if (mover == null || pos == null) return;
        for (IEntity other : getCollisionsAt(pos, mover)) {
            if (other instanceof PowerUp) {
                other.onCollide(mover);
            } else {
                mover.onCollide(other);
            }
        }
    }

    public TileType getTileAt(Position pos) {
        if (pos.getRow() < 0 || pos.getRow() >= map.getHeight()
                || pos.getCol() < 0 || pos.getCol() >= map.getWidth()) {
            return null;
        }
        return map.getTiles()[pos.getRow()][pos.getCol()];
    }
}
