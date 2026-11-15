package nabla.singleton;

import java.io.IOException;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Konfigurasi global untuk Nangor Blast.
 * <p>
 * Field bersifat statis dan bisa diubah runtime (misalnya oleh test).
 * TODO bagusnya pakai JSON or no ya
 */
public final class Config {
    public static String defaultMapLayout = null;
    public static String defaultMapName = null;
    public static int TARGET_FPS = 60;
    public static double TARGET_DT = 1 / 60.0;

    /* TODO make sure ini bisa diconfig sendiri */
    public static int DEFAULT_LIVES = 3;
    public static int MAX_LIVES = 5;
    public static int DEFAULT_BOMB_CAPACITY = 1;
    public static int MAX_BOMB_CAPACITY = 3;
    public static int DEFAULT_BLAST_RANGE = 1;
    public static int MAX_BLAST_RANGE = 5;
    public static double DEFAULT_SPEED = 1.0;
    public static double RESPAWN_INVULNERABLE_TIME = 1.5;
    /** Durasi kebal setelah menerima damage, mencegah damage tiap tick (F03). */
    public static final double HIT_INVULNERABLE_TIME = 1.0;

    private Config() {}

    static {
        /* Apakah bagus dihardcode or no */
        String configDir = "config/";

        try {
          String[] mapRaw = Files.readString(Path.of(configDir + "map.conf")).trim().split("\n");
          defaultMapName = mapRaw[0];
          /* unix: \n vs. windows: \r\n jadi pake lineSeparator() */
          defaultMapLayout = String.join(System.lineSeparator(), Arrays.copyOfRange(mapRaw, 1, mapRaw.length));

          /* TODO pake JSON buat conf*/
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public static final double DEFAULT_TIMER_SECONDS = 300;
}
