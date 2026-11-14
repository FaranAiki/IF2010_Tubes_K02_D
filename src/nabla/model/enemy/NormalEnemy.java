package nabla.model.enemy;

import nabla.model.component.HealthStats;
import nabla.model.entity.Entity;
import nabla.type.EnemyBehavior;
import nabla.util.Position;

/**
 * Normal Enemy -- musuh dasar (F05.2).
 * <p>
 * Setiap map memiliki tepat 5 Normal Enemy. Masing-masing punya satu perilaku:
 * PATROL (selalu mengikuti jalur patrol) atau CHASE (player dalam 3 tile -> menyerang).
 * <p>
 * <b>Prinsip:</b>
 * <ul>
 *   <li><b>Strategy Pattern:</b> Perilaku (patrol/ chase) diatur oleh
 *       {@link IEnemyBehavior} yang bisa diganti tanpa ubah class Enemy.</li>
 *   <li><b>Composition over Inheritance:</b> Menggunakan komponen
 *       {@link HealthStats} untuk nyawa, bukan field privat.</li>
 * </ul>
 */
public class NormalEnemy extends Entity {
    private final EnemyBehavior behavior;
    private final HealthStats health = new HealthStats(1, 1);
    private final java.util.List<Position> patrolPath;
    private int patrolIndex = 0;
    private double speed;
    private int scoreValue = 100;

    public NormalEnemy(Position position, EnemyBehavior behavior,
                        java.util.List<Position> patrolPath, double speed) {
        super(position);
        this.behavior = behavior;
        this.patrolPath = patrolPath != null ? patrolPath : new java.util.ArrayList<>();
        this.speed = speed;
    }

    @Override
    public void update(double deltaTime) {
        if (!isActive()) return;
        /* Logika patrol/ chase diatur oleh GameLoop (atau strategy di sini) */
        /* Untuk skeleton, kita update patrol index */
        if (behavior == EnemyBehavior.PATROL && !patrolPath.isEmpty()) {
            Position target = patrolPath.get(patrolIndex);
            if (position.isSameTile(target)) {
                patrolIndex = (patrolIndex + 1) % patrolPath.size();
            } else {
                /* Bergerak ke arah target (simplified) */
                if (target.getRow() > position.getRow()) {
                    position.setRow(position.getRow() + 1);
                } else if (target.getRow() < position.getRow()) {
                    position.setRow(position.getRow() - 1);
                } else if (target.getCol() > position.getCol()) {
                    position.setCol(position.getCol() + 1);
                } else if (target.getCol() < position.getCol()) {
                    position.setCol(position.getCol() - 1);
                }
            }
        }
    }

    public void takeDamage(int amount) {
        if (health.takeDamage(amount)) {
            deactivate();
        }
    }

    public boolean isDead() {
        return health.isDead();
    }

    public EnemyBehavior getBehavior() { return behavior; }
    public HealthStats getHealth() { return health; }
    public java.util.List<Position> getPatrolPath() { return patrolPath; }
    public int getPatrolIndex() { return patrolIndex; }
    public void setPatrolIndex(int i) { patrolIndex = i; }
    public double getSpeed() { return speed; }
    public int getScoreValue() { return scoreValue; }

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