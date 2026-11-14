package nabla.model.component;

import nabla.type.BombType;

/**
 * Komponen inventory bom (kapasitas & active bombs).
 * <p>
 * <b>Prinsip Composition over Inheritance</b>.
 * Dipakai oleh Player (dan bisa oleh Enemy untuk bom yang ditembakkan).
 * Menangani kapasitas maksimum, bom aktif, dan pemilihan type.
 */
public class BombInventory extends Inventory<BombType> {

    /**
     * Konstruktor BombInventory.
     *
     * @param capacity kapasitas maksimum bom
     * @param selectedType tipe bom yang dipilih saat ini
     */
    public BombInventory(int capacity, BombType selectedType) {
        super(capacity, selectedType);
    }

    /**
     * Alias untuk tryUse() - coba pasang bom.
     *
     * @return true jika bom berhasil dipasang, false jika kapasitas penuh
     */
    public boolean tryPlace() {
        return tryUse();
    }
}