package nabla.model;

import nabla.model.collision.Collidable;
import nabla.util.Position;
import nabla.util.Updatable;

/**
 * Kelas abstrak dasar untuk semua entitas di map.
 * <p>
 * Entity hanya urus posisi dan status aktif, bukan logika gerak/serang.
 * Kelas lain (Bomb, PowerUp) bisa menggunakan Position tanpa memaksa inheritance.
 */
public abstract class Entity implements Collidable, Updatable {
    protected Position position;
    protected boolean active;

    protected Entity(Position position) {
        this.position = position;
        this.active = true;
    }

    public Position getPosition() { return position; }

    public boolean isActive() { return active; }

    public void deactivate() { active = false; }

    public abstract void update(double deltaTime);

    public void applyExplosion(int damage, Position center, int range) {
        /* default: tidak ada reaksi terhadap ledakan */
    }

    public boolean blocksMovement() { return false; }

    @Override
    public void onCollide(Collidable other) {
        /* default: no response */
    }

    public boolean overlaps(Entity other) {
        return getPosition() != null && other != null
                && other.getPosition() != null
                && getPosition().isSameTile(other.getPosition());
    }
}
