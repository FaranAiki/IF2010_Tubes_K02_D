package nabla.exception;

/**
 * Exception yang dilemparkan ketika peta game tidak memenuhi validasi F00.3.
 *
 * <p>Digunakan oleh {@link nabla.model.entity.Map#validate()} untuk
 * memberitahukan bahwa layout peta tidak valid (misalkan tidak ada spawn player,
 * tidak ada pintu keluar, atau jumlah enemy spawn tidak sesuai).
 *
 * @param message pesan error yang menjelaskan alasan validasi gagal
 */
public class InvalidMapException extends RuntimeException {
    /**
     * Konstruktor exception dengan pesan error.
     *
     * @param message pesan error yang menjelaskan alasan validasi gagal
     */
    public InvalidMapException(String message) {
        super(message);
    }
}