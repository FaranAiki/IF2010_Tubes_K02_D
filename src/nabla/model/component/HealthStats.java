package nabla.model.component;

/**
 * Health system untuk entity (player, enemy).
 * <p>
 * Mengelola nyawa maksimal dan nyawa saat ini, dengan method takeDamage/heal/reset.
 * Ini digunakan untuk mengganti lives karena bisa dire-use.
 */
public class HealthStats {
    private final int maxLives;
    private int lives;

    public HealthStats(int initialLives, int maxLives) {
        this.maxLives = maxLives;
        this.lives = initialLives;
    }

    public boolean takeDamage(int amount) {
        lives -= amount;
        return lives <= 0;
    }

    public void heal(int amount) {
        lives = Math.min(lives + amount, maxLives);
    }

    public boolean isDead() { return lives <= 0; }

    public void reset() { lives = maxLives; }

    public int getLives() { return lives; }
    public int getMaxLives() { return maxLives; }
    public int getCurrent() { return lives; }
    public int getMax() { return maxLives; }
}
