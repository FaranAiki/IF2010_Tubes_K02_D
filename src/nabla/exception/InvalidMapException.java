package nabla.exception;

/**
 * Exception yang dilemparkan ketika peta game tidak memenuhi validasi F00.3.
 *
 * <p>Digunakan oleh {@link nabla.model.world.Map#validate()} untuk
 * memberitahukan bahwa layout peta tidak valid (misalnya tidak ada spawn player,
 * tidak ada pintu keluar, atau jumlah enemy spawn tidak sesuai)
 * [atau ketentuan lainnya].
 */
public class InvalidMapException extends RuntimeException {
    public InvalidMapException(String message) {
        super(message);
    }
}
