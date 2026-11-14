package nabla.model.bomb;

import nabla.model.entity.Entity;
import nabla.model.entity.Player;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Bomb (abstract) — senjata utama player (F02).
 * <p>
 * <b>Prinsip:</b>
 * <ul>
 *   <li><b>Template Method:</b> `update()` adalah template yang diatur oleh kelas induk.
 *       Subclass hanya override method yang diperlukan (mis. fuse countdown).</li>
 *   <li><b>Factory Method:</b> Pembuatan bom dilakukan oleh `BombFactory`,
 *       bukan langsung `new NormalBomb(...)`. Jadi kita bisa tambahkan
 *       bom baru tanpa ubah code caller.</li>
 * </ul>
 */
public abstract class Bomb extends Entity {
    protected final BombType type;
    protected final Player owner;
    protected double fuseTime;     /* detik, tersisa sebelum ledak */
    protected boolean triggered;   /* true jika sudah dipicu (chain reaction) */
    protected int blastRange;

    protected Bomb(BombType type, Player owner, Position position, double fuseTime, int blastRange) {
        super(position);
        this.type = type;
        this.owner = owner;
        this.fuseTime = fuseTime;
        this.blastRange = blastRange;
    }

    /** Update bomb (template method). */
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

    /** Apakah bom ini punya fuse time? (Remote Bomb tidak punya). */
    protected boolean hasFuse() {
        return true;
    }

    /** Dipanggil ketika bom dipicu (oleh timer atau ledakan lain). */
    public void trigger() {
        if (!isActive()) return;
        triggered = true;
    }

    /** Aksi yang dilakukan ketika bom meledak. */
    protected abstract void onDetonate();

    /** Buat Explosion sesuai aturan masing-masing bom. */
    public abstract Explosion createExplosion();

    /* --- Getters --- */
    public BombType getType() { return type; }
    public Player getOwner() { return owner; }
    public double getFuseTime() { return fuseTime; }
    public boolean isTriggered() { return triggered; }
    public int getBlastRange() { return blastRange; }

    @Override
    public boolean blocksMovement() {
        return isActive(); /* bom aktif menghalangi pergerakan */
    }
}