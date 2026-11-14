package nabla.model.strategy;

import nabla.util.Direction;
import nabla.util.Position;

/**
 * Strategy Pattern. Interface untuk perilaku pergerakan entitas.
 * <p>
 * <b>Design Pattern Open/Closed dan Composition over Inheritance</b>.
 * Daripada Player punya method `move()` yang if-else berdasarkan tile type
 * (buka untuk diubah, tutup untuk ekstensi), kita ekstrak menjadi strategy.
 * Dengan demikian, kita bisa tambahkan perilaku baru (IceSlide, WaterSlow, LavaEruption)
 * tanpa mengubah code Player.
 * <p>
 * Bentuk konkretnya berupa `IceSlideStrategy`, `WaterSlowStrategy`, dan `NormalMoveStrategy`.
 */
public interface IMovementStrategy {
    /**
     * Coba pindahkan entitas dari posisi saat ini ke arah yang diinginkan.
     *
     * @param current   posisi sekarang
     * @param direction arah gerak yang diinginkan
     * @param canMove   fungsi untuk mengecek apakah tile tujuan dapat dilalui
     * @return Position baru (mungkin sama dengan current jika terhalang),
     *         atau current jika strategy memutuskan pergerakan (misal slide).
     */
    Position tryMove(Position current, Direction direction, java.util.function.BiPredicate<Position, Direction> canMove);
}
