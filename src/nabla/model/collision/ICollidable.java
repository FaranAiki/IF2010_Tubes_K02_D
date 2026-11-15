package nabla.model.collision;

/**
 * Interface untuk entitas yang bisa berpartisipasi dalam collision detection dan response.
 * <p>
 * Memisahkan collision logic dari IEntity agar IEntity tetap generic untuk GameLoop.
 */
public interface ICollidable<T extends ICollidable<T>> {
    default void onCollide(T other) {
        /* Default: tidak ada response */
    }
}