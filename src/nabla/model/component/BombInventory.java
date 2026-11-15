package nabla.model.component;

import nabla.type.BombType;

/**
 * Inventory bom player.
 * <p>
 * Mengelola kapasitas bom, tipe bom yang dipilih, dan jumlah bom aktif.
 */
public class BombInventory {
    private int capacity;
    private int active;
    private BombType selectedType;

    public BombInventory(int capacity, BombType selectedType) {
        this.capacity = capacity;
        this.active = 0;
        this.selectedType = selectedType;
    }

    public boolean tryPlace() {
        if (active < capacity) {
            active++;
            return true;
        }
        return false;
    }

    public void release() {
        if (active > 0) active--;
    }

    public void addCapacity(int amount, int maxCapacity) {
        capacity = Math.min(capacity + amount, maxCapacity);
    }

    public void reset(int capacity, BombType type) {
        this.capacity = capacity;
        this.active = 0;
        this.selectedType = type;
    }

    public int getCapacity() { return capacity; }
    public int getActive() { return active; }
    public BombType getSelectedType() { return selectedType; }
    public void setSelectedType(BombType type) { this.selectedType = type; }
    public boolean isFull() { return active >= capacity; }
}
