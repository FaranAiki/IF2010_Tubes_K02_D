package nabla.type;

/**
 * Konfigurasi statis untuk satu pola serangan boss.
 * Mengikuti F05.3 - Mekanisme Serangan Boss.
 * <p>
 * Berupa data holder (POJO). Pola serangan spesifik per boss
 * (Fire Breath, Swoop, Tail Splash, ...) didefinisikan di konfigurasi masing-masing boss.
 */
public class AttackPattern {
    private final String name;
    private final AttackArea area;
    private final double duration;     /* detik, aktif setelah warning */
    private final double warningTime;   /* detik, default 1.0 */
    private final int phase;            /* 1 atau 2 */

    public AttackPattern(String name, AttackArea area, double duration, double warningTime, int phase) {
        this.name = name;
        this.area = area;
        this.duration = duration;
        this.warningTime = warningTime;
        this.phase = phase;
    }

    public String getName() { return name; }
    public AttackArea getArea() { return area; }
    public double getDuration() { return duration; }
    public double getWarningTime() { return warningTime; }
    public int getPhase() { return phase; }

    @Override
    public String toString() { return name; }
}
