package nabla.util;

/* Umumnya untuk size Map */
public class Size {
    /* Karena Map berukuran default 14x10*/
    private int width = 14;
    private int height = 10;

    public Size(int w, int h) {
        height = h;
        width = w;
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

    public void setHeight(int h) {
        height = h;
    }

    public void setWidth(int w) {
        width = w;
    }
}
