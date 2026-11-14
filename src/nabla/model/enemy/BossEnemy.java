package nabla.model.enemy;

import nabla.model.component.HealthStats;
import nabla.model.entity.Entity;
import nabla.type.AttackPattern;
import nabla.util.Position;

/**
 * Boss Enemy -- musuh utama pada map (F05.3).
 * <p>
 * Setiap Boss memiliki health 8 (kecuali Cerberus dan Altair yang punya 4),
 * dua fase, dan pola serangan spesifik. Boss menjadi rentan setelah
 * menyelesaikan serangan tertentu.
 * <p>
 * <ul>
 *   <li><b>Design Pattern Strategy Pattern</b> Pola serangan diatur oleh
 *       {@link IBossAttackStrategy}, bukan if-else di method update().</li>
 *   <li><b>Design Pattern Composition over Inheritance</b> Menggunakan komponen
 *       {@link HealthStats} untuk nyawa, bukan field privat.</li>
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

    /**
     * Konstruktor BossEnemy.
     *
     * @param position posisi boss pada grid
     * @param maxHealth nyawa maksimum boss
     */
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

    /**
     * Cek apakah boss sudah mati.
     *
     * @return true jika boss sudah mati
     */
    public boolean isDead() {
        return health.isDead();
    }

    /**
     * Terima damage (hanya jika rentan).
     *
     * @param amount jumlah damage yang diterima
     */
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

    /**
     * Override oleh subclass untuk atur attack pattern phase 2.
     */
    protected void onPhaseChange() {
        /* Default: tidak ada aksi */
    }

    /**
     * Set cooldown setelah serangan.
     *
     * @param cooldown waktu cooldown dalam detik
     */
    public void setAttackCooldown(double cooldown) {
        this.attackCooldown = cooldown;
    }

    /**
     * Set rentan selama waktu tertentu.
     *
     * @param time waktu rentan dalam detik
     */
    public void setVulnerable(double time) {
        this.vulnerable = true;
        this.vulnerableTime = time;
    }

    /**
     * Mendapatkan komponen health boss.
     *
     * @return HealthStats boss
     */
    public HealthStats getHealth() { return health; }

    /**
     * Mendapatkan fase saat ini.
     *
     * @return fase boss (1 atau 2)
     */
    public int getCurrentPhase() { return currentPhase; }

    /**
     * Mendapatkan sisa cooldown serangan.
     *
     * @return sisa cooldown dalam detik
     */
    public double getAttackCooldown() { return attackCooldown; }

    /**
     * Cek apakah boss dalam status rentan.
     *
     * @return true jika rentan
     */
    public boolean isVulnerable() { return vulnerable; }

    /**
     * Mendapatkan daftar attack pattern untuk fase saat ini.
     *
     * @return daftar AttackPattern
     */
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
