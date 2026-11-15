package nabla.model.entity;

import nabla.util.Position;

/**
 * Kontrak umum untuk semua entitas di map (Player, Enemy, Bomb, Explosion).
 * <p>
 * GameLoop hanya tahu tentang IEntity, tidak peduli apakah itu Player, Enemy, atau Bomb.
 * Interface ini memungkinkan code di GameLoop menjadi generic dan mudah diuji.
 */
public interface IEntity extends Collidable {
    void update(double deltaTime);
    boolean isActive();
    void deactivate();
    Position getPosition();
    boolean blocksMovement();
    void applyExplosion(int damage, Position center, int range);

    default boolean overlaps(IEntity other) {
        return getPosition() != null && other != null
                && other.getPosition() != null
                && getPosition().isSameTile(other.getPosition());
    }
}
