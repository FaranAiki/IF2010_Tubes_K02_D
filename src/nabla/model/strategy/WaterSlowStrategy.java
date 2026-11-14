package nabla.model.strategy;

import nabla.model.collision.CollisionChecker;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Pergerakan pada Water Tile. Kecepatan player diperlambat 50% (F07.3.2 Blue Resort).
 * <p>
 * Strategy ini hanya mengembalikan posisi tujuan (gerakan 1 tile),
 * penanganan kecepatan dilakukan oleh GameLoop berdasarkan flag dari strategy.
 */
public class WaterSlowStrategy implements IMovementStrategy {
    @Override
    public Position tryMove(Position current, Direction direction, CollisionChecker checker) {
        if (checker.canMove(current, direction)) {
            return direction.deltaPosition().add(current);
        }
        return current;
    }
}