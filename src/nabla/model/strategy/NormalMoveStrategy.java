package nabla.model.strategy;

import nabla.model.strategy.IMovementStrategy;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Pergerakan normal: cek apakah tile tujuan dapat dilalui, jika ya pindah.
 * <p>
 * <b>Prinsip:</b> Strategy Pattern — concrete strategy yang paling sederhana.
 * Bisa digunakan oleh Player (dan Enemy) pada tile biasa.
 */
public class NormalMoveStrategy implements IMovementStrategy {
    @Override
    public Position tryMove(Position current, Direction direction,
                            java.util.function.BiPredicate<Position, Direction> canMove) {
        if (canMove.test(current, direction)) {
            return direction.deltaPosition().add(current);
        }
        return current;
    }
}