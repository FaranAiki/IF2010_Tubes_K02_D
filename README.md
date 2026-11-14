Ini tugas besar bom bom

Untuk *package*, digunakan `nabla` yang merupakan singkatan dari Nangor Blast (nabla lebih keren dan singkat).

## Anotasi dan Komentar pada Kode Sumber

Setiap kode pada kode sumber ini harus mematuhi komentar Javadocs,
[lanjutkan]

## Hierarki
- `config/` - konfigurasi
- `doc/` - dokumentasi dan milestone
- `src/nabla/` - kode sumber (root package `nabla`)
  - `Main.java` - entry point (`nabla.Main`)
  - `exception/` - exception kustom
  - `game/` - game loop & concurrency (GameLoop, InputManager)
  - `gui/` - tampilan/GUI (ConsoleRenderer, JavaFX nanti)
  - `input/` - input player (WASD, arrows, dsb.)
  - `model/` - entitas (Entity, Player, Enemy, Bomb, Block, Map, Game)
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
Karena kode sumber ini sangat modular, kita bisa mengganti menjadi CLI (konsol), JavaFX, Swing, atau bahkan library lainnya. Jika ingin memakai JavaFX (Untuk JDK 17 ke atas, JavaFX dipisah dari JDK), gunakan

1. **Install (Arch Linux)**
   ```bash
   sudo pacman -S java-openjfx
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
   (Sesuaikan `--add-modules` jika butuh modul lain, misalnya `javafx.graphics`.

