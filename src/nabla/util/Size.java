package nabla.util;

/**
 * Ukuran map (lebar dan tinggi).
 * <p>
 * Prinsip: Value Object. Digunakan oleh Map untuk menyimpan dimensi
 * tanpa menggunakan static final fields.
 */
public class Size {
    private int width = 14;
    private int height = 10;

    /**
     * Konstruktor Size.
     *
     * @param w lebar map
     * @param h tinggi map
     */
    public Size(int w, int h) {
        height = h;
        width = w;
    }

    /**
     * Mendapatkan lebar.
     *
     * @return lebar map
     */
    public int getWidth() { return this.width; }

    /**
     * Mendapatkan tinggi.
     *
     * @return tinggi map
     */
    public int getHeight() { return this.height; }

    /**
     * Mengatur tinggi map.
     *
     * @param h tinggi baru
     */
    public void setHeight(int h) {
        height = h;
    }

    /**
     * Mengatur lebar map.
     *
     * @param w lebar baru
     */
    public void setWidth(int w) {
        width = w;
    }
}
