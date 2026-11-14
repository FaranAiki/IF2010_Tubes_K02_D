package nabla.model.bomb;

import nabla.model.entity.Entity;
import nabla.model.entity.Player;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Bomb (abstract) — senjata utama player (F02).
 * <p>
 * <ul>
 *   <li><b>Design Pattern Template Method</b> {@link #update(double)} adalah template yang diatur
 *       oleh kelas induk. Subclass hanya override method yang diperlukan
 *       (misalnya fuse countdown).</li>
 *   <li><b>Design Pattern Factory Method</b> Pembuatan bom dilakukan oleh {@link BombFactory},
 *       bukan langsung {@code new NormalBomb(...)}. Jadi kita bisa tambahkan
 *       bom baru tanpa ubah code caller.</li>
 * </ul>
 */
public abstract class Bomb extends Entity {
    protected final BombType type;
    protected final Player owner;
    protected double fuseTime;     /* detik, tersisa sebelum ledak */
    protected boolean triggered;   /* true jika sudah dipicu (chain reaction) */
    protected int blastRange;

    /**
     * Konstruktor Bomb.
     *
     * @param type tipe bom
     * @param owner pemilik bom (player)
     * @param position posisi bom pada grid
     * @param fuseTime waktu meledak (detik)
     * @param blastRange jangkauan ledakan
     */
    protected Bomb(BombType type, Player owner, Position position, double fuseTime, int blastRange) {
        super(position);
        this.type = type;
        this.owner = owner;
        this.fuseTime = fuseTime;
        this.blastRange = blastRange;
    }

    /**
     * Update bomb (template method).
     *
     * @param deltaTime selang waktu sejak update terakhir (detik)
     */
    @Override
    public void update(double deltaTime) {
        if (!isActive()) return;
        if (triggered) {
            /* Sudah dipicu, ledakkan */
            onDetonate();
            deactivate();
            return;
        }
        /* Fuse countdown (hanya untuk bom yang punya fuse time) */
        if (hasFuse()) {
            fuseTime -= deltaTime;
            if (fuseTime <= 0) {
                triggered = true;
            }
        }
    }

    /**
     * Cek apakah bom ini punya fuse time.
     * Remote Bomb tidak punya fuse time.
     *
     * @return true jika bom memiliki fuse time
     */
    protected boolean hasFuse() {
        return true;
    }

    /**
     * Memicu bom untuk meledak (oleh timer atau ledakan lain).
     */
    public void trigger() {
        if (!isActive()) return;
        triggered = true;
    }

    /**
     * Aksi yang dilakukan ketika bom meledak.
     */
    protected abstract void onDetonate();

    /**
     * Buat Explosion sesuai aturan masing-masing bom.
     *
     * @return objek Explosion baru
     */
    public abstract Explosion createExplosion();

    /* --- Getters --- */
    /**
     * Mendapatkan tipe bom.
     *
     * @return tipe bom
     */
    public BombType getType() { return type; }

    /**
     * Mendapatkan pemilik bom.
     *
     * @return player pemilik bom
     */
    public Player getOwner() { return owner; }

    /**
     * Mendapatkan waktu fuse yang tersisa.
     *
     * @return waktu fuse dalam detik
     */
    public double getFuseTime() { return fuseTime; }

    /**
     * Cek apakah bom sudah dipicu.
     *
     * @return true jika sudah dipicu
     */
    public boolean isTriggered() { return triggered; }

    /**
     * Mendapatkan jangkauan ledakan.
     *
     * @return jangkauan ledakan dalam tile
     */
    public int getBlastRange() { return blastRange; }

    @Override
    public boolean blocksMovement() {
        return isActive(); /* bom aktif menghalangi pergerakan */
    }
}