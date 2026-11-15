package nabla.model.entity;

import nabla.util.Position;

/**
 * Kelas abstrak dasar untuk semua entitas di map.
 * <p>
 * Entity hanya urus posisi dan status aktif, bukan logika gerak/serang.
 * Kelas lain (Bomb, PowerUp) bisa menggunakan Position tanpa memaksa inheritance.
 */
public abstract class Entity implements IEntity {
    protected Position position;
    protected boolean active;

    protected Entity(Position position) {
        this.position = position;
        this.active = true;
    }

    @Override
    public Position getPosition() { return position; }

    @Override
    public boolean isActive() { return active; }

    @Override
    public void deactivate() { active = false; }

    @Override
    public void applyExplosion(int damage, Position center, int range) {
        /* default: tidak ada reaksi terhadap ledakan */
    }

    @Override
    public boolean blocksMovement() { return false; }

    @Override
    public void onCollide(Collidable other) {
        /* default: no response */
    }

    @Override
    public boolean overlaps(IEntity other) {
        return getPosition() != null && other != null
                && other.getPosition() != null
                && getPosition().isSameTile(other.getPosition());
    }
}
