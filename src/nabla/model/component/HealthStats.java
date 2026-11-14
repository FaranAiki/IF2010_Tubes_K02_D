package nabla.model.component;

/**
 * Komponen nyawa (Health).
 * <p>
 * <b>Prinsip Composition over Inheritance</b>.
 * Daripada Player & Enemy masing-masing punya field `lives`/`health`,
 * kita buat komponen terpisah yang bisa digunakan oleh keduanya.
 * Jadi kita bisa tambahi effect (misalkan invulnerability, regen) di satu tempat.
 */
public class HealthStats {
    private int current;
    private final int max;

    /**
     * Konstruktor HealthStats dengan current = max.
     *
     * @param max nyawa maksimum
     */
    public HealthStats(int max) {
        this.max = max;
        this.current = max;
    }

    /**
     * Konstruktor HealthStats dengan nilai current dan max yang ditentukan.
     *
     * @param current nyawa saat ini
     * @param max nyawa maksimum
     */
    public HealthStats(int current, int max) {
        this.current = current;
        this.max = max;
    }

    /**
     * Kurangi nyawa sejumlah amount.
     *
     * @param amount jumlah damage yang diterima
     * @return true jika nyawa habis (current <= 0)
     */
    public boolean takeDamage(int amount) {
        current = Math.max(0, current - amount);
        return current <= 0;
    }

    /**
     * Tambah nyawa (tidak melebihi max).
     *
     * @param amount jumlah yang ditambahkan
     */
    public void heal(int amount) {
        current = Math.min(max, current + amount);
    }

    /**
     * Set ulang ke nilai maksimum.
     */
    public void reset() {
        current = max;
    }

    /**
     * Mendapatkan nyawa saat ini.
     *
     * @return nyawa saat ini
     */
    public int getCurrent() { return current; }

    /**
     * Mendapatkan nyawa maksimum.
     *
     * @return nyawa maksimum
     */
    public int getMax() { return max; }

    /**
     * Cek apakah nyawa habis.
     *
     * @return true jika nyawa <= 0
     */
    public boolean isDead() { return current <= 0; }

    @Override
    public String toString() {
        return current + "/" + max;
    }
}