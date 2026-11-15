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
