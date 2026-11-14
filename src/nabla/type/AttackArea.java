package nabla.type;

/**
 * Area serangan musuh.
 * <p>
 * Mengikuti F05.3 - Mekanisme Serangan Boss.
 * Setiap konstanta menggambarkan bentuk area yang terpengaruh serangan.
 */
public enum AttackArea {
    /** Garis lurus ke depan/arah hadap. */
    LINE_STRAIGHT,
    /** Satu baris atau kolom yang melewati target. */
    LINE_ROW_COL,
    /** 8 tile di sekeliling (radius 1, termasuk diagonal). */
    AROUND_8,
    /** 24 tile di sekeliling (radius 2). */
    AROUND_24,
    /** Area 3x3. */
    AROUND_3x3,
    /** Area 5x5. */
    AROUND_5x5,
    /** Area 3x4 (depan). */
    AROUND_3x4,
    /** Area 3x2 (depan). */
    AROUND_3x2,
    /** 3x3 di depan. */
    FRONT_3x3,
    /** 3 tile di depan (lebar). */
    FRONT_3,
    /** Projectile ke arah/posisi tertentu. */
    PROJECTILE,
    /** Tile target spesifik. */
    TARGET_TILE,
    /** Beberapa tile target (misalkan Missile Barrage). */
    MULTIPLE_TARGETS
}
