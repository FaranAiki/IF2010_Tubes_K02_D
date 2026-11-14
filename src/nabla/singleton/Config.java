package nabla.singleton;

/**
 * Konfigurasi global untuk Nangor Blast.
 * <p>
 * Field bersifat statis dan bisa diubah runtime (misalnya oleh test).
 */
public final class Config {
    private Config() {}

    /* --- Game Loop --- */
    public static int TARGET_FPS = 60;
    public static double getTargetDt() {
        return 1.0 / TARGET_FPS;
    }

    /* --- Map --- */
    public static final String DEFAULT_MAP_NAME = "Green Garden";
    public static final String MAP_RESOURCE_DIR = "maps/";

    /* --- Player --- */
    public static final int DEFAULT_LIVES = 3;
    public static final int MAX_LIVES = 5;
    public static final int DEFAULT_BOMB_CAPACITY = 1;
    public static final int MAX_BOMB_CAPACITY = 3;
    public static final int DEFAULT_BLAST_RANGE = 1;
    public static final int MAX_BLAST_RANGE = 5;
    public static final double DEFAULT_SPEED = 1.0;
    public static final double MAX_SPEED = 2.0;
    public static final double RESPAWN_INVULNERABLE_TIME = 1.5;

    /* --- Game --- */
    public static final int DEFAULT_TIMER_SECONDS = 300;
}