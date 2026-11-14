package nabla.model.bomb;

import nabla.model.entity.Entity;
import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Explosion — efek ledakan (F03).
 * <p>
 * <b>Design Pattern Explosion adalah data holder</b> yang diatur oleh GameLoop.
 * Ledakan menyebar ke 4 arah dan berhenti ketika mengenai block (F03.2).
 */
public class Explosion extends Entity {
    private final int blastRange;
    private final Player owner;
    private double duration;     /* detik, tersisa */
    private boolean applied;      /* true jika damage/block destruction sudah diproses */

    /**
     * Konstruktor Explosion.
     *
     * @param position posisi ledakan
     * @param blastRange jangkauan ledakan
     * @param owner pemilik bom yang meledak
     * @param duration durasi ledakan (detik)
     */
    public Explosion(Position position, int blastRange, Player owner, double duration) {
        super(position);
        this.blastRange = blastRange;
        this.owner = owner;
        this.duration = duration;
        this.applied = false;
    }

    @Override
    public void update(double deltaTime) {
        if (!isActive()) return;
        duration -= deltaTime;
        if (duration <= 0) {
            deactivate();
        }
    }

    /**
     * Mendapatkan jangkauan ledakan.
     *
     * @return jangkauan ledakan dalam tile
     */
    public int getBlastRange() { return blastRange; }

    /**
     * Mendapatkan pemilik bom yang meledak.
     *
     * @return player pemilik
     */
    public Player getOwner() { return owner; }

    /**
     * Mendapatkan durasi ledakan yang tersisa.
     *
     * @return durasi dalam detik
     */
    public double getDuration() { return duration; }

    /**
     * Cek apakah damage/block destruction sudah diproses.
     *
     * @return true jika sudah diproses
     */
    public boolean isApplied() { return applied; }

    /**
     * Set status apakah damage sudah diproses.
     *
     * @param v status baru
     */
    public void setApplied(boolean v) { this.applied = v; }
}