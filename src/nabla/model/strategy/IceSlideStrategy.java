package nabla.model.strategy;

import nabla.model.collision.CollisionChecker;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Pergerakan pada Ice Tile. Player terus bergerak mengikuti arah terakhirnya
 * dan tidak dapat mengubah arah menghadap (F07.3.4 White Glacier).
 * <p>
 * Player berhenti pada tile biasa yang dapat dilalui atau tile sebelum penghalang.
 */
public class IceSlideStrategy implements IMovementStrategy {
    @Override
    public Position tryMove(Position current, Direction direction, CollisionChecker checker) {
        Position next = current;
        Direction dir = direction;
        /* Slide terus hingga keluar dari Ice atau terhalang */
        while (checker.isIceTile(next)) {
            Position candidate = dir.deltaPosition().add(next);
            if (!checker.canMove(next, dir)) {
                break; /* terhalang, berhenti di posisi sebelumnya */
            }
            next = candidate;
            if (!checker.isIceTile(next)) {
                break; /* sudah keluar dari Ice, berhenti */
            }
        }
        return next;
    }
}