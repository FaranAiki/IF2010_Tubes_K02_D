package nabla.model.component;

import nabla.util.Updatable;

/**
 * Komponen stat serangan (damage, range, cooldown).
 * <p>
 * <b>Design Pattern Composition over Inheritance</b>.
 * Dipakai oleh Enemy (serangan fisik) dan Boss (serangan spesifik).
 * Jadi kita bisa atur damage dan cooldown di satu tempat.
 */
public class AttackStats implements Updatable {
    private int damage;
    private double cooldown;
    private double currentCooldown;

    public AttackStats(int damage, double cooldown) {
        this.damage = damage;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
    }

    public void update(double deltaTime) {
        if (currentCooldown > 0) {
            currentCooldown = Math.max(0, currentCooldown - deltaTime);
        }
    }

    public boolean isReady() {
        return currentCooldown <= 0;
    }

    public void fire() {
        currentCooldown = cooldown;
    }

    public void reset() {
        currentCooldown = 0;
    }

    public int getDamage() { return damage; }

    public double getCooldown() { return cooldown; }

    public double getCurrentCooldown() { return currentCooldown; }

    public void setDamage(int d) { this.damage = d; }

    public void setCooldown(double c) { this.cooldown = c; }
}
