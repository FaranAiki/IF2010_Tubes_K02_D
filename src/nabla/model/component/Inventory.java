package nabla.model.component;

/**
 * Base class untuk inventory generik.
 * <p>
 * <b>Prinsip:</b> Template Method Pattern.
 * Subclass hanya perlu implementasikan logika spesifik (mis. kapasitas, item type).
 */
public abstract class Inventory<T> {
    protected int capacity;
    protected int active;
    protected T selectedType;

    public Inventory(int capacity, T selectedType) {
        this.capacity = capacity;
        this.active = 0;
        this.selectedType = selectedType;
    }

    /**
     * Coba gunakan satu slot inventory.
     * @return true jika berhasil, false jika penuh
     */
    public boolean tryUse() {
        if (active >= capacity) return false;
        active++;
        return true;
    }

    /**
     * Kembalikan satu slot inventory (mis. setelah item digunakan/selesai).
     */
    public void release() {
        if (active > 0) active--;
    }

    /**
     * Tambah kapasitas maksimum.
     * @param amount jumlah yang ditambah
     * @param max kapasitas maksimum
     */
    public void addCapacity(int amount, int max) {
        capacity = Math.min(capacity + amount, max);
    }

    /**
     * Reset ke nilai awal.
     * @param initialCapacity kapasitas awal
     * @param initialType type awal
     */
    public void reset(int initialCapacity, T initialType) {
        capacity = initialCapacity;
        active = 0;
        selectedType = initialType;
    }

    public int getCapacity() { return capacity; }
    public int getActive() { return active; }
    public T getSelectedType() { return selectedType; }
    public void setSelectedType(T type) { this.selectedType = type; }
    public boolean isFull() { return active >= capacity; }
}