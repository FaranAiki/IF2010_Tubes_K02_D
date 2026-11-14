package nabla.model.strategy;

import nabla.model.strategy.IMovementStrategy;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Pergerakan pada Ice Tile: player terus bergerak mengikuti arah terakhirnya
 * dan tidak dapat mengubah arah menghadap (F07.3.4 White Glacier).
 * <p>
 * Player berhenti pada tile biasa yang dapat dilalui atau tile sebelum penghalang.
 * Strategy ini memerlukan predicate untuk mengetahui apakah suatu tile adalah Ice.
 */
public class IceSlideStrategy implements IMovementStrategy {
    private final java.util.function.Predicate<Position> isIceTile;

    public IceSlideStrategy(java.util.function.Predicate<Position> isIceTile) {
        this.isIceTile = isIceTile;
    }

    @Override
    public Position tryMove(Position current, Direction direction,
                            java.util.function.BiPredicate<Position, Direction> canMove) {
        Position next = current;
        Direction dir = direction;
        /* Slide terus hingga keluar dari Ice atau terhalang */
        while (isIceTile.test(next)) {
            Position candidate = dir.deltaPosition().add(next);
            if (!canMove.test(next, dir)) {
                break; /* terhalang, berhenti di posisi sebelumnya */
            }
            next = candidate;
            if (!isIceTile.test(next)) {
                break; /* sudah keluar dari Ice, berhenti */
            }
        }
        return next;
    }
}