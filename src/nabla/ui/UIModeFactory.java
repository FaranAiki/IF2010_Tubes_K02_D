package nabla.ui;

/**
 * Factory provider untuk mendapatkan UIFactory berdasarkan mode UI.
 * <p>
 * Menggunakan registry pattern untuk mapping string mode ke factory.
 * <p>
 * <b>Design Pattern Abstract Factory Pattern</b> Client hanya butuh nama mode,
 * tidak perlu tahu kelas factory konkret. Ini berguna karena kita bahkan
 * bisa membuat mode UI untuk Android, bahkan web.
 */
public final class UIModeFactory {
    private UIModeFactory() {
        /* Prevent instantiation */
    }

    /**
     * Mendapatkan factory untuk mode UI yang diminta.
     *
     * Konversi dari String menjadi Factory yang diinginkan
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
     */
    public static UIFactory getDefaultFactory() {
        return new nabla.ui.javafx.JavaFXFactory();
    }
}
