package nabla.model.strategy;

import nabla.model.strategy.IMovementStrategy;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Pergerakan pada Water Tile: kecepatan player diperlambat 50% (F07.3.2 Blue Resort).
 * Strategy ini hanya mengembalikan posisi tujuan (gerakan 1 tile),
 * penanganan kecepatan dilakukan oleh GameLoop berdasarkan flag dari strategy.
 */
public class WaterSlowStrategy implements IMovementStrategy {
    @Override
    public Position tryMove(Position current, Direction direction,
                            java.util.function.BiPredicate<Position, Direction> canMove) {
        if (canMove.test(current, direction)) {
            return direction.deltaPosition().add(current);
        }
        return current;
    }
}