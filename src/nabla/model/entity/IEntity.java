package nabla.model.entity;

import nabla.util.Position;

/**
 * Kontrak umum untuk semua entitas yang ada di map (Player, Enemy, Bomb, Explosion).
 * <p>
 * <b>Prinsip:</b> Liskov Substitution Principle — GameLoop hanya tahu tentang
 * `IEntity`, tidak peduli apakah itu Player, Enemy, atau Bomb. Semua entitas
 * bisa di-update melalui `update(double)` dan di-check collision melalui `getPosition()`.
 * <p>
 * Interface ini memungkinkan code di GameLoop menjadi generic dan mudah diuji.
 */
public interface IEntity {
    /** Update state entitas berdasarkan delta time (detik). */
    void update(double deltaTime);

    /** Apakah entitas masih aktif/ada di map? */
    boolean isActive();

    /** Nonaktifkan entitas (dihapus dari map). */
    void deactivate();

    /** Posisi saat ini pada grid map. */
    Position getPosition();

    /** Apakah entitas menghalangi pergerakan entity lain? */
    boolean blocksMovement();

    /** Apakah entitas terkena ledakan/area effect? */
    void applyExplosion(int damage, Position center, int range);
}