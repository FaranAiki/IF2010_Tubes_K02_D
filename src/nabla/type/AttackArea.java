package nabla.type;

/* Bagaimana musuh menyerang dan digunakan enum */
public enum AttackArea {
    LINE_STRAIGHT,   /* garis lurus ke depan/arah hadap */
    LINE_ROW_COL,    /* satu baris atau kolom yang melewati target */
    AROUND_8,        /* 8 tile di sekeliling (radius 1, termasuk diagonal)*/
    AROUND_24,       /* 24 tile di sekeliling (radius 2)*/
    AROUND_3x3,      /* area 3x3*/
    AROUND_5x5,      /* area 5x5*/
    AROUND_3x4,      /* area 3x4 (depan)*/
    AROUND_3x2,      /* area 3x2 (depan)*/
    FRONT_3x3,       /* 3x3 di depan*/
    FRONT_3,         /* 3 tile di depan (lebar)*/
    PROJECTILE,      /* projectile ke arah/posisi tertentu*/
    TARGET_TILE,     /* tile target spesifik*/
    MULTIPLE_TARGETS  /* beberapa tile target (mis. Missile Barrage)*/
}
