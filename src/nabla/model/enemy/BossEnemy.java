package nabla.model.enemy;

import nabla.model.component.AttackStats;
import nabla.model.component.HealthStats;
import nabla.model.Entity;
import nabla.type.AttackPattern;
import nabla.util.Position;
import java.util.List;

/**
 * Boss Enemy sebagai musuh utama pada map (F05.3).
 * <p>
 * Setiap Boss memiliki health 8 (kecuali Cerberus dan Altair yang punya 4),
 * dua fase, dan pola serangan spesifik. Boss menjadi rentan setelah
 * menyelesaikan serangan tertentu.
 * <p>
 * <ul>
 *   <li><b>Design Pattern Composition over Inheritance</b> Menggunakan komponen
 *       {@link HealthStats} untuk nyawa dan {@link AttackStats} untuk serangan.</li>
 * </ul>
 */
public abstract class BossEnemy extends Entity {
    protected HealthStats health;
    protected AttackStats attackStats;
    protected int currentPhase = 1;
    protected boolean vulnerable = false;
    protected double vulnerableTime = 0;
    protected java.util.List<AttackPattern> phase1Attacks;
    protected java.util.List<AttackPattern> phase2Attacks;
    protected AttackPattern lastAttack = null;

    protected BossEnemy(Position position, int maxHealth) {
        super(position);
        this.health = new HealthStats(maxHealth, maxHealth);
        this.attackStats = new AttackStats(2, 2.0);
        this.phase1Attacks = new java.util.ArrayList<>();
        this.phase2Attacks = new java.util.ArrayList<>();
    }

    @Override
    public void update(double deltaTime) {
        if (!isActive()) return;

        if (vulnerableTime > 0) {
            vulnerableTime -= deltaTime;
            if (vulnerableTime <= 0) {
                vulnerable = false;
            }
        }

        attackStats.update(deltaTime);
    }

    public boolean isDead() {
        return health.isDead();
    }

    public void takeDamage(int amount) {
        if (!vulnerable) return;
        if (health.takeDamage(amount)) {
            deactivate();
            return;
        }
        if (currentPhase == 1 && health.getCurrent() <= health.getMax() / 2) {
            currentPhase = 2;
            onPhaseChange();
        }
    }

    protected void onPhaseChange() {
        /* Default: tidak ada aksi */
    }

    public void setVulnerable(double time) {
        this.vulnerable = true;
        this.vulnerableTime = time;
    }

    public HealthStats getHealth() { return health; }
    public AttackStats getAttackStats() { return attackStats; }
    public int getCurrentPhase() { return currentPhase; }
    public boolean isVulnerable() { return vulnerable; }

    public java.util.List<AttackPattern> getCurrentPhaseAttacks() {
        return currentPhase == 1 ? phase1Attacks : phase2Attacks;
    }

    @Override
    public boolean blocksMovement() {
        return isActive();
    }

    @Override
    public void applyExplosion(int damage, List<Position> affectedTiles) {
        if (!isActive() || affectedTiles == null) return;
        for (Position tile : affectedTiles) {
            if (position.isSameTile(tile)) {
                takeDamage(damage);
                return;
            }
        }
    }
}