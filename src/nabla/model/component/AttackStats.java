package nabla.model.component;

/**
 * Komponen stat serangan (damage, range, cooldown).
 * <p>
 * <b>Prinsip Composition over Inheritance</b>.
 * Dipakai oleh Enemy (serangan fisik) dan Boss (serangan spesifik).
 * Jadi kita bisa atur damage dan cooldown di satu tempat.
 */
public class AttackStats {
    private int damage;
    private double cooldown;
    private double currentCooldown;

    /**
     * Konstruktor AttackStats.
     *
     * @param damage jumlah damage per serangan
     * @param cooldown waktu cooldown antara serangan (detik)
     */
    public AttackStats(int damage, double cooldown) {
        this.damage = damage;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
    }

    /**
     * Update cooldown timer.
     *
     * @param deltaTime selang waktu sejak update terakhir (detik)
     */
    public void update(double deltaTime) {
        if (currentCooldown > 0) {
            currentCooldown = Math.max(0, currentCooldown - deltaTime);
        }
    }

    /**
     * Cek apakah siap menyerang.
     *
     * @return true jika cooldown sudah habis
     */
    public boolean isReady() {
        return currentCooldown <= 0;
    }

    /**
     * Set cooldown setelah serangan.
     */
    public void fire() {
        currentCooldown = cooldown;
    }

    /**
     * Reset cooldown ke nol.
     */
    public void reset() {
        currentCooldown = 0;
    }

    /**
     * Mendapatkan jumlah damage.
     *
     * @return damage per serangan
     */
    public int getDamage() { return damage; }

    /**
     * Mendapatkan waktu cooldown.
     *
     * @return cooldown dalam detik
     */
    public double getCooldown() { return cooldown; }

    /**
     * Mendapatkan sisa cooldown saat ini.
     *
     * @return sisa cooldown dalam detik
     */
    public double getCurrentCooldown() { return currentCooldown; }

    /**
     * Mengatur jumlah damage.
     *
     * @param d damage baru
     */
    public void setDamage(int d) { this.damage = d; }

    /**
     * Mengatur waktu cooldown.
     *
     * @param c cooldown baru dalam detik
     */
    public void setCooldown(double c) { this.cooldown = c; }
}
