package nabla.model.strategy;

import nabla.model.collision.CollisionChecker;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Pergerakan normal: cek apakah tile tujuan dapat dilalui, jika ya pindah.
 * Bisa digunakan oleh Player (dan Enemy) pada tile biasa.
 */
public class NormalMoveStrategy implements IMovementStrategy {
    @Override
    public Position tryMove(Position current, Direction direction, CollisionChecker checker) {
        if (checker.canMove(current, direction)) {
            return direction.deltaPosition().add(current);
        }
        return current;
    }
}