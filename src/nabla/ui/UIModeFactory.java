package nabla.ui;

/**
 * Factory provider untuk mendapatkan UIFactory berdasarkan mode UI.
 * <p>
 * Menggunakan registry pattern untuk mapping string mode ke factory.
 * <p>
 * <b>Prinsip Abstract Factory Pattern</b> Client hanya butuh nama mode,
 * tidak perlu tahu kelas factory konkret.
 */
public final class UIModeFactory {
    private UIModeFactory() {
        /* Prevent instantiation */
    }

    /**
     * Mendapatkan factory untuk mode UI yang diminta.
     *
     * @param mode nama mode UI ("cli", "swing", "javafx")
     * @return UIFactory yang sesuai, atau null jika mode tidak dikenal
     */
    public static UIFactory getFactory(String mode) {
        return switch (mode.toLowerCase()) {
            case "cli" -> new nabla.ui.cli.CLIFactory();
            case "swing" -> new nabla.ui.swing.SwingFactory();
            case "javafx" -> new nabla.ui.javafx.JavaFXFactory();
            default -> null;
        };
    }

    /**
     * Mendapatkan factory default (JavaFX).
     *
     * @return UIFactory untuk JavaFX
     */
    public static UIFactory getDefaultFactory() {
        return new nabla.ui.javafx.JavaFXFactory();
    }
}