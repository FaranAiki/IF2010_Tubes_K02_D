package nabla.input;

/**
 * Kontrak untuk semua aksi gameplay yang bisa dilakukan pemain.
 * <p>
 * <b>Prinsip</b> DIP (Dependency Inversion)
 * GameLoop hanya bergantung pada interface ini, bukan pada
 * implementasi khusus keyboard.
 *
 * InputHandler (concrete) mengimplementasikannya. Dengan demikian,
 * kita bisa mengganti sumber input (keyboard, file test, jaringan) tanpa ubah GameLoop.
 * <p>
 * Setiap aksi mengembalikan true jika aksi benar-benar terjadi
 * (misalnya, gerakan terhalang -> false).
 */
public interface IGameplayAction {
    /** Gerak player ke atas (W). */
    boolean moveUp();

    /** Gerak player ke bawah (S). */
    boolean moveDown();

    /** Gerak player ke kiri (A). */
    boolean moveLeft();

    /** Gerak player ke kanan (D). */
    boolean moveRight();

    /** Pasang bom di tile player (B). */
    boolean placeBomb();

    /** Pilih bom berikutnya (E -> Pumped Bomb). */
    boolean selectNextBombType();

    /** Detonasi semua Remote Bomb aktif (Q). */
    boolean detonateRemote();

    /** Jeda/lanjutkan permainan (P). */
    boolean togglePause();

    /** Restart permainan (R). */
    boolean restart();

    /** Kembali ke menu utama (ESC). */
    boolean backToMenu();
}
