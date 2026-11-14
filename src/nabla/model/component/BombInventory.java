package nabla.model.component;

import nabla.type.BombType;

/**
 * Komponen inventory bom (kapasitas & active bombs).
 * <p>
 * <b>Prinsip:</b> Composition over Inheritance.
 * Dipakai oleh Player (dan bisa oleh Enemy untuk bom yang ditembakkan).
 * Menangani kapasitas maksimum, bom aktif, dan pemilihan type.
 */
public class BombInventory extends Inventory<BombType> {

    public BombInventory(int capacity, BombType selectedType) {
        super(capacity, selectedType);
    }

    /** Alias untuk tryUse() - coba pasang bom. */
    public boolean tryPlace() {
        return tryUse();
    }
}