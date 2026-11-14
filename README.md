# Nangor Blast (BOOM!)

## TODO
1. Implementasi CLI kemudian GUI menggunakan JavaFX (program utama) dan menggunakan Swing (bukti modularitas)
2. Masih ada yang belom pakai standar JavaDoc

## Anggota Kelompok
18225122 Muhammad Faran Aiki
[lanjutkan]

## Packaging

Untuk *package*, digunakan `nabla` yang merupakan singkatan dari Nangor Blast (nabla lebih keren dan singkat).

## Prinsip

Kode sumber ini sebisa mungkin sangat modular sehingga penambahan fitur, misal integrasi ke Android,
Player bisa punya inventory lain, dan sebagainya, sangat mudah dilakukan.

## Anotasi dan Komentar pada Kode Sumber

Setiap kode pada kode sumber ini harus mematuhi komentar Javadocs sehingga formatnya seragam
dan untuk mempermudah komunikasi antaranggota (apalagi bagi yang belum sama sekali menyentuh).

Lebih baik anotasi sedini mungkin karena kalau sudah kelar, bisa jadi malas buat menulis komen
dan malah bisa kelupaan sedang menulis apa.

## Hierarki
- `config/` - konfigurasi
- `doc/` - dokumentasi dan milestone
- `src/nabla/` - kode sumber (root package `nabla`)
  - `Main.java` - entry point (`nabla.Main`)
  - `exception/` - exception kustom
- `game/` - game loop & concurrency (GameLoop, MapLoader)
- `ui/` - tampilan/GUI (ConsoleRenderer, JavaFX nanti)
- `input/` - input player (WASD, arrows, dsb.)
- `model/`
  - `entity/` - entitas dasar (Entity, Player, IEntity)
  - `world/` - peta (Map)
  - `game/` - state manager (Game)
  - `block/`, `bomb/`, `component/`, `enemy/`, `powerup/`, `strategy/` - subsystem model
- `singleton/` - konfigurasi global (Config)
- `type/` - enum/tipe (GameState, BombType, PowerUpType, BlockType, TileType, EnemyBehavior, AttackPattern)
- `util/` - kelas pembantu (`Position`, `Direction`, `Size`)
- `test/in/`, `test/out/` - kasus uji (input/output)
- `Makefile` - build/run/test (di root)

## Build & Run
```bash
make        # compile src/**/*.java -> bin/
make run    # jalankan nabla.Main (java -cp bin nabla.Main)
make test   # bandingkan test/in/*.txt dengan test/out/*.txt
make clean  # hapus bin/
```
> `java Main` tidak bisa karena kelas ada di package `nabla`; gunakan `java -cp bin nabla.Main` atau `make run`.

## GUI (JavaFX) - setup
Karena kode sumber ini sangat modular, kita bisa mengganti menjadi CLI (konsol), JavaFX, Swing, atau bahkan library gui lainnya. Jika ingin memakai JavaFX (Untuk JDK 17 ke atas, JavaFX dipisah dari JDK), gunakan (untuk Arch Linux, untuk Windows nanti)

1. **Install (Arch Linux)**
   ```bash
   # Tidak ada di repo Arch yang community, jadi pakai yay
   sudo yay -S java-openjfx
   ```
2. **Cari path library JavaFX**
   ```bash
   find /usr -name "javafx.controls.jar" 2>/dev/null
   # Contohnya: /usr/lib/jvm/java-17-openjdk/lib atau /usr/share/openjfx/lib
   ```
3. **Compile dan run dengan module path**
   ```bash
   JAVAFX_LIB=/path/ke/lib-javafx   # sesuaikan hasil langkah 2
   javac --module-path "$JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml -d bin $(find src -name "*.java")
   java  --module-path "$JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml -cp bin nabla.Main
   ```
   (Sesuaikan `--add-modules` jika butuh modul lain, misalnya `javafx.graphics`).

## Lainnya
