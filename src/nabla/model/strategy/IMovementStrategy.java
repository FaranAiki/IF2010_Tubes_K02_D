package nabla.model.strategy;

import nabla.model.collision.CollisionChecker;
import nabla.util.Direction;
import nabla.util.Position;

/**
 * Strategy Pattern. Interface untuk perilaku pergerakan entitas.
 * <p>
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
     * <p>
     * Collision detection didelegasikan ke CollisionChecker, bukan di strategy.
     * Strategy hanya memutuskan pola gerakan (normal, slide, lambat).
     *
     * @param current   posisi sekarang
     * @param direction arah gerak yang diinginkan
     * @param checker   collision checker untuk validasi
     * @return Position baru (mungkin sama dengan current jika terhalang),
     *         atau current jika strategy memutuskan pergerakan (misal slide).
     */
    Position tryMove(Position current, Direction direction, CollisionChecker checker);
}