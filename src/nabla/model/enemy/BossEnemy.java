package nabla.model.enemy;

import nabla.model.component.HealthStats;
import nabla.model.entity.Entity;
import nabla.type.AttackPattern;
import nabla.util.Position;

/**
 * Boss Enemy -- musuh utama pada map (F05.3).
 * <p>
 * Setiap Boss memiliki health 8 (kecuali Cerberus & Altair yang punya 4),
 * dua fase, dan pola serangan spesifik. Boss menjadi rentan setelah
 * menyelesaikan serangan tertentu.
 * <p>
 * <b>Prinsip:</b>
 * <ul>
 *   <li><b>Strategy Pattern:</b> Pola serangan diatur oleh
 *       {@link IBossAttackStrategy}, bukan if-else di method update().</li>
 *   <li><b>Composition over Inheritance:</b> Menggunakan komponen
 *       {@link Health}, bukan field privat.</li>
 * </ul>
 */
public abstract class BossEnemy extends Entity {
    protected HealthStats health;
    protected int currentPhase = 1;
    protected double attackCooldown = 0;
    protected boolean vulnerable = false;
    protected double vulnerableTime = 0;
    protected java.util.List<AttackPattern> phase1Attacks;
    protected java.util.List<AttackPattern> phase2Attacks;
    protected AttackPattern lastAttack = null;

    protected BossEnemy(Position position, int maxHealth) {
        super(position);
        this.health = new HealthStats(maxHealth, maxHealth);
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

        if (attackCooldown > 0) {
            attackCooldown -= deltaTime;
        }
    }

    /** Cek apakah boss sudah mati. */
    public boolean isDead() {
        return health.isDead();
    }

    /** Terima damage (hanya jika rentan). */
    public void takeDamage(int amount) {
        if (!vulnerable) return;
        if (health.takeDamage(amount)) {
            deactivate();
            return;
        }
        /* Cek phase 2: health mencapai setengah maksimum */
        if (currentPhase == 1 && health.getCurrent() <= health.getMax() / 2) {
            currentPhase = 2;
            onPhaseChange();
        }
    }

    /** Override oleh subclass untuk atur attack pattern phase 2. */
    protected void onPhaseChange() {
        /* Default: tidak ada aksi */
    }

    /** Set cooldown setelah serangan. */
    public void setAttackCooldown(double cooldown) {
        this.attackCooldown = cooldown;
    }

    /** Set rentan selama tertentu (detik). */
    public void setVulnerable(double time) {
        this.vulnerable = true;
        this.vulnerableTime = time;
    }

    public HealthStats getHealth() { return health; }
    public int getCurrentPhase() { return currentPhase; }
    public double getAttackCooldown() { return attackCooldown; }
    public boolean isVulnerable() { return vulnerable; }
    public java.util.List<AttackPattern> getCurrentPhaseAttacks() {
        return currentPhase == 1 ? phase1Attacks : phase2Attacks;
    }

    @Override
    public boolean blocksMovement() {
        return isActive();
    }

    @Override
    public void applyExplosion(int damage, Position center, int range) {
        if (position.distanceTo(center) <= range) {
            takeDamage(damage);
        }
    }
}