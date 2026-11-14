package nabla.type;

/**
 * Konfigurasi statis untuk satu pola serangan boss.
 * <p>
 * Mengikuti F05.3 - Mekanisme Serangan Boss.
 * Berupa data holder (POJO). Pola serangan spesifik per boss
 * (Fire Breath, Swoop, Tail Splash, ...) didefinisikan di konfigurasi masing-masing boss.
 */
public class AttackPattern {
    private final String name;
    private final AttackArea area;
    private final double duration;     /* detik, aktif setelah warning */
    private final double warningTime;   /* detik, default 1.0 */
    private final int phase;            /* 1 atau 2 */

    /**
     * Konstruktor AttackPattern.
     *
     * @param name nama pola serangan
     * @param area area yang terpengaruh
     * @param duration durasi aktif setelah warning (detik)
     * @param warningTime waktu peringatan sebelum serangan (detik)
     * @param phase fase boss (1 atau 2)
     */
    public AttackPattern(String name, AttackArea area, double duration, double warningTime, int phase) {
        this.name = name;
        this.area = area;
        this.duration = duration;
        this.warningTime = warningTime;
        this.phase = phase;
    }

    /**
     * Mendapatkan nama pola serangan.
     *
     * @return nama pola serangan
     */
    public String getName() { return name; }

    /**
     * Mendapatkan area serangan.
     *
     * @return area serangan
     */
    public AttackArea getArea() { return area; }

    /**
     * Mendapatkan durasi aktif setelah warning.
     *
     * @return durasi dalam detik
     */
    public double getDuration() { return duration; }

    /**
     * Mendapatkan waktu peringatan sebelum serangan.
     *
     * @return waktu peringatan dalam detik
     */
    public double getWarningTime() { return warningTime; }

    /**
     * Mendapatkan fase boss.
     *
     * @return fase boss (1 atau 2)
     */
    public int getPhase() { return phase; }

    @Override
    public String toString() { return name; }
}
