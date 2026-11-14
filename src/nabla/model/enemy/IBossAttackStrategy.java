package nabla.model.enemy;

import nabla.type.AttackPattern;

/**
 * Strategy Pattern -- kontrak untuk pola serangan boss.
 * <p>
 * Setiap Boss (Draco, Leviathan, ...) mengimplementasikan strategy ini.
 * Jadi GameLoop hanya memanggil `selectAttack()` tanpa tahu detail boss.
 */
public interface IBossAttackStrategy {
    /**
     * Pilih pola serangan acak dari phase saat ini.
     * Tidak boleh mengulangi serangan yang sama berturut-turut (F05.3.1.3).
     */
    AttackPattern selectAttack(int currentPhase, AttackPattern lastAttack);

    /** Cek apakah boss rentan setelah serangan ini selesai. */
    boolean becomesVulnerableAfter(AttackPattern attack);

    /** Waktu cooldown setelah serangan (default 5 detik). */
    default double getCooldown() {
        return 5.0;
    }
}