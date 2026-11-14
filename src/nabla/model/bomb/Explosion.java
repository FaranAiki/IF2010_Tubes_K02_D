package nabla.model.bomb;

import nabla.model.entity.Entity;
import nabla.model.entity.Player;
import nabla.util.Position;

/**
 * Explosion — efek ledakan (F03).
 * <p>
 * <b>Prinsip:</b> Explosion adalah data holder yang diatur oleh GameLoop.
 * Ledakan menyebar ke 4 arah dan berhenti ketika mengenai block (F03.2).
 */
public class Explosion extends Entity {
    private final int blastRange;
    private final Player owner;
    private double duration;     /* detik, tersisa */
    private boolean applied;      /* true jika damage/block destruction sudah diproses */

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

    public int getBlastRange() { return blastRange; }
    public Player getOwner() { return owner; }
    public double getDuration() { return duration; }
    public boolean isApplied() { return applied; }
    public void setApplied(boolean v) { this.applied = v; }
}