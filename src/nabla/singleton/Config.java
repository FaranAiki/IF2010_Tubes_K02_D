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
    public static String mapLayout = null;
    public static String mapName = null;

    private Config() {}

    static {
        /* Apakah bagus dihardcode or no */
        String configDir = "config/";

        try {
          String[] mapRaw = Files.readString(Path.of(configDir + "map.conf")).trim().split("\n");
          mapName = mapRaw[0];
          mapLayout = String.join(System.lineSeparator(), Arrays.copyOfRange(mapRaw, 1, mapRaw.length));
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public static int TARGET_FPS = 60;
    public static double getTargetDt() {
        return 1.0 / TARGET_FPS;
    }

    public static final int DEFAULT_TIMER_SECONDS = 300;
}
