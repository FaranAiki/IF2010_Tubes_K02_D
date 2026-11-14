package nabla.model.component;

/**
 * Komponen nyawa (Health).
 * <p>
 * <b>Prinsip:</b> Composition over Inheritance.
 * Daripada Player & Enemy masing-masing punya field `lives`/`health`,
 * kita buat komponen terpisah yang bisa digunakan oleh keduanya.
 * Jadi kita bisa tambahi effect (mis. invulnerability, regen) di satu tempat.
 */
public class HealthStats {
    private int current;
    private final int max;

    public HealthStats(int max) {
        this.max = max;
        this.current = max;
    }

    public HealthStats(int current, int max) {
        this.current = current;
        this.max = max;
    }

    /** Kurangi 1 nyawa. Return true jika habis. */
    public boolean takeDamage(int amount) {
        current = Math.max(0, current - amount);
        return current <= 0;
    }

    /** Tambah nyawa (tidak melewati max). */
    public void heal(int amount) {
        current = Math.min(max, current + amount);
    }

    /** Set ulang ke nilai maksimum. */
    public void reset() {
        current = max;
    }

    public int getCurrent() { return current; }
    public int getMax() { return max; }
    public boolean isDead() { return current <= 0; }

    @Override
    public String toString() {
        return current + "/" + max;
    }
}