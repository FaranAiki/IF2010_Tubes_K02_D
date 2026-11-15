package nabla.model;

import nabla.model.collision.ICollidable;
import nabla.util.Position;
import nabla.util.IUpdatable;

import java.util.List;

/**
 * Kelas abstrak dasar untuk semua entitas di map.
 * <p>
 * Entity hanya urus posisi dan status aktif, bukan logika gerak/serang.
 * Kelas lain (Bomb, PowerUp) bisa menggunakan Position tanpa memaksa inheritance.
 *
 * Pada kode sumber ini, Entity didefinisikan sebagai sesuatu yg menimpa tile.
 */
public abstract class Entity implements ICollidable<Entity>, IUpdatable {
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

    public void applyExplosion(int damage, List<Position> affectedTiles) {
        /* default: tidak ada reaksi terhadap ledakan */
    }

    public boolean blocksMovement() { return false; }

    @Override
    public void onCollide(Entity other) {
        /* default: no response */
    }

    public boolean overlaps(Entity other) {
        return getPosition() != null && other != null
                && other.getPosition() != null
                && getPosition().isSameTile(other.getPosition());
    }
}
