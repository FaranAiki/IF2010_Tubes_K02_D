package nabla.model.entity;

/**
 * Interface untuk entitas yang bisa berpartisipasi dalam collision detection dan response.
 * <p>
 * Memisahkan collision logic dari IEntity agar IEntity tetap generic untuk GameLoop.
 * Tidak menggunakan enum tag agar open/closed - entity baru tidak perlu ubah enum.
 */
public interface Collidable {
    default void onCollide(Collidable other) {
        /* Default: tidak ada response */
    }
}
