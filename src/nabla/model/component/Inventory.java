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

    public Inventory(int capacity, T selectedType) {
        this.capacity = capacity;
        this.active = 0;
        this.selectedType = selectedType;
    }

    public boolean tryUse() {
        if (active >= capacity) return false;
        active++;
        return true;
    }

    public void release() {
        if (active > 0) active--;
    }

    public void addCapacity(int amount, int max) {
        capacity = Math.min(capacity + amount, max);
    }

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
