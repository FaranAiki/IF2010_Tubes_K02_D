package nabla.model.bomb;

import nabla.model.Entity;
import nabla.model.Player;
import nabla.model.bomb.ICanUseBomb;
import nabla.type.BombType;
import nabla.util.Position;

/**
 * Explosion sebagai efek ledakan (F03).
 * <p>
 * <b>Design Pattern Explosion adalah data holder</b> yang diatur oleh GameLoop.
 * Ledakan menyebar ke 4 arah dan berhenti ketika mengenai block (F03.2).
 * <p>
 * Reinforced Block hanya bisa dihancurkan oleh ledakan dari Pumped Bomb.
 */
public class Explosion extends Entity {
    private final int blastRange;
    private final ICanUseBomb owner;
    private final BombType bombType;
    private double duration;     /* detik tersisa TODO: make sure di GUI ini tuh keliatan */
    private boolean applied;      /* true jika damage/block destruction sudah diproses */
    private int damage;

    public Explosion(Position position, int blastRange, ICanUseBomb owner, double duration, BombType bombType) {
        super(position);
        this.blastRange = blastRange;
        this.owner = owner;
        this.bombType = bombType;
        this.duration = duration;
        this.applied = false;
        this.damage = 1;
    }

    public Explosion(Position position, int blastRange, ICanUseBomb owner, double duration) {
        this(position, blastRange, owner, duration, BombType.NORMAL);
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

    public ICanUseBomb getOwner() { return owner; }

    public BombType getBombType() { return bombType; }

    public double getDuration() { return duration; }

    public boolean isApplied() { return applied; }

    public void setApplied(boolean v) { this.applied = v; }

    public int getDamage() { return damage; }
}
