package nabla.model.entity;

import nabla.util.Position;
import nabla.util.Size;

/**
 * Kelas abstrak dasar untuk semua entitas di map.
 * <p>
 * <b>Prinsip:</b>
 * <ul>
 *   <li><b>Composition over Inheritance:</b> Entity <i>memiliki</i> `Position`
 *       (field), bukan extends `Position`. Jadi Position bisa digunakan oleh
 *       kelas lain (mis. Bomb, PowerUp) tanpa memaksa hierarki inheritance.</li>
 *   <li><b>Single Responsibility:</b> Kelas ini hanya urus posisi & status aktif,
 *       bukan logika gerak/serang.</li>
 * </ul>
 */
public abstract class Entity implements IEntity {
    protected Position position;
    protected boolean active;

    protected Entity(Position position) {
        this.position = position;
        this.active = true;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public void applyExplosion(int damage, Position center, int range) {
        /* default: tidak ada reaksi terhadap ledakan */
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }
}