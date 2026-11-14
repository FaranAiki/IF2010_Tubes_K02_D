package nabla.model.component;

/**
 * Base class untuk inventory generik.
 * <p>
 * <b>Design Pattern Template Method Pattern</b>.
 * Subclass hanya perlu implementasikan logika spesifik (misalnya kapasitas, item type).
 */
public abstract class Inventory<T> {
    protected int capacity;
    protected int active;
    protected T selectedType;

    /**
     * Konstruktor Inventory.
     *
     * @param capacity kapasitas maksimum inventory
     * @param selectedType tipe item yang dipilih saat ini
     */
    public Inventory(int capacity, T selectedType) {
        this.capacity = capacity;
        this.active = 0;
        this.selectedType = selectedType;
    }

    /**
     * Coba gunakan satu slot inventory.
     *
     * @return true jika berhasil, false jika penuh
     */
    public boolean tryUse() {
        if (active >= capacity) return false;
        active++;
        return true;
    }

    /**
     * Kembalikan satu slot inventory (misalnya setelah item digunakan/selesai).
     */
    public void release() {
        if (active > 0) active--;
    }

    /**
     * Tambah kapasitas maksimum.
     *
     * @param amount jumlah yang ditambah
     * @param max kapasitas maksimum
     */
    public void addCapacity(int amount, int max) {
        capacity = Math.min(capacity + amount, max);
    }

    /**
     * Reset ke nilai awal.
     *
     * @param initialCapacity kapasitas awal
     * @param initialType type awal
     */
    public void reset(int initialCapacity, T initialType) {
        capacity = initialCapacity;
        active = 0;
        selectedType = initialType;
    }

    /**
     * Mendapatkan kapasitas maksimum.
     *
     * @return kapasitas maksimum
     */
    public int getCapacity() { return capacity; }

    /**
     * Mendapatkan jumlah item yang sedang aktif.
     *
     * @return jumlah item aktif
     */
    public int getActive() { return active; }

    /**
     * Mendapatkan tipe item yang dipilih.
     *
     * @return tipe item terpilih
     */
    public T getSelectedType() { return selectedType; }

    /**
     * Mengatur tipe item yang dipilih.
     *
     * @param type tipe item baru
     */
    public void setSelectedType(T type) { this.selectedType = type; }

    /**
     * Cek apakah inventory penuh.
     *
     * @return true jika penuh
     */
    public boolean isFull() { return active >= capacity; }
}