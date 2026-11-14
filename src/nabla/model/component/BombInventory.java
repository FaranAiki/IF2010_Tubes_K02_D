package nabla.model.component;

import nabla.type.BombType;

/**
 * Inventory bom player.
 * <p>
 * Mengelola kapasitas bom, tipe bom yang dipilih, dan jumlah bom aktif.
 */
public class BombInventory extends Inventory<BombType> {
    public BombInventory(int capacity, BombType selectedType) {
        super(capacity, selectedType);
    }

    public boolean tryPlace() {
        if (active < capacity) {
            active++;
            return true;
        }
        return false;
    }

    @Override
    public void release() {
        if (active > 0) active--;
    }

    @Override
    public void addCapacity(int amount, int maxCapacity) {
        capacity = Math.min(capacity + amount, maxCapacity);
    }

    @Override
    public void reset(int capacity, BombType type) {
        this.capacity = capacity;
        this.active = 0;
        this.selectedType = type;
    }
}