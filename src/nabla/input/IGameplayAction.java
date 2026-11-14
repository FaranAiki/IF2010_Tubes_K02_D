package nabla.input;

/**
 * Kontrak untuk semua aksi gameplay yang bisa dilakukan pemain.
 * <p>
 * <b>Prinsip DIP</b> (Dependency Inversion)
 * GameLoop hanya bergantung pada interface ini, bukan pada
 * implementasi khusus keyboard.
 * <p>
 * InputHandler (concrete) mengimplementasikannya. Dengan demikian,
 * kita bisa mengganti sumber input (keyboard, file test, jaringan) tanpa ubah GameLoop.
 * <p>
 * Setiap aksi mengembalikan true jika aksi benar-benar terjadi
 * (misalnya, gerakan terhalang -> false).
 */
public interface IGameplayAction {
    /**
     * Gerak player ke atas (W).
     *
     * @return true jika player berhasil bergerak, false jika terhalang
     */
    boolean moveUp();

    /**
     * Gerak player ke bawah (S).
     *
     * @return true jika player berhasil bergerak, false jika terhalang
     */
    boolean moveDown();

    /**
     * Gerak player ke kiri (A).
     *
     * @return true jika player berhasil bergerak, false jika terhalang
     */
    boolean moveLeft();

    /**
     * Gerak player ke kanan (D).
     *
     * @return true jika player berhasil bergerak, false jika terhalang
     */
    boolean moveRight();

    /**
     * Pasang bom di tile player (B).
     *
     * @return true jika bom berhasil dipasang, false jika kapasitas penuh
     */
    boolean placeBomb();

    /**
     * Pilih bom berikutnya (E -> Pumped Bomb).
     *
     * @return true jika tipe bom berhasil diganti
     */
    boolean selectNextBombType();

    /**
     * Detonasi semua Remote Bomb aktif (Q).
     *
     * @return true jika minimal satu Remote Bomb berhasil didetonasi
     */
    boolean detonateRemote();

    /**
     * Jeda/lanjutkan permainan (P).
     *
     * @return true jika state permainan berhasil diubah
     */
    boolean togglePause();

    /**
     * Restart permainan (R).
     *
     * @return true jika permainan berhasil di-restart
     */
    boolean restart();

    /**
     * Kembali ke menu utama (ESC).
     *
     * @return true jika berhasil kembali ke menu utama
     */
    boolean backToMenu();
}
