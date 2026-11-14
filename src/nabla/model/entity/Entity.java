package nabla.model.entity;

import nabla.util.Position;
import nabla.util.Size;

/**
 * Kelas abstrak dasar untuk semua entitas di map.
 * <p>
 * <ul>
 *   <li><b>Prinsip Composition over Inheritance</b> Entity <i>memiliki</i> `Position`
 *       (field), bukan extends `Position`. Jadi Position bisa digunakan oleh
 *       kelas lain (misalkan Bomb, PowerUp) tanpa memaksa hierarki inheritance.</li>
 *   <li><b>Prinsip Single Responsibility</b> Kelas ini hanya urus posisi & status aktif,
 *       bukan logika gerak/serang.</li>
 * </ul>
 */
public abstract class Entity implements IEntity {
    protected Position position;
    protected boolean active;

    /**
     * Konstruktor Entity.
     *
     * @param position posisi awal entitas
     */
    protected Entity(Position position) {
        this.position = position;
        this.active = true;
    }

    /**
     * Mendapatkan posisi entitas pada grid.
     *
     * @return posisi entitas
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Cek apakah entitas masih aktif.
     *
     * @return true jika aktif
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Nonaktifkan entitas (dihapus dari map).
     */
    @Override
    public void deactivate() {
        active = false;
    }

    /**
     * Terapkan efek ledakan pada entitas.
     * Default: tidak ada reaksi terhadap ledakan.
     *
     * @param damage jumlah damage
     * @param center pusat ledakan
     * @param range jangkauan ledakan
     */
    @Override
    public void applyExplosion(int damage, Position center, int range) {
        /* default: tidak ada reaksi terhadap ledakan */
    }

    /**
     * Cek apakah entitas menghalangi pergerakan entity lain.
     *
     * @return true jika menghalangi pergerakan
     */
    @Override
    public boolean blocksMovement() {
        return false;
    }
}