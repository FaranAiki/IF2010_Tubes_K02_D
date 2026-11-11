SRC_DIR := src
BIN_DIR := bin
MAIN_CLASS := nabla.Main

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

.PHONY: all build run clean test

all: build

# Hanya untuk check build saja
build:
	@mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES)

# Karena MAIN_CLASS := nabla.Main, kita harus run menggunakan java -cp ...
run: build
	java -cp $(BIN_DIR) $(MAIN_CLASS)

# TODO
test: build
	@pass=0; fail=0; \
	for in in test/in/*.txt; do \
		[ -f "$$in" ] || continue; \
		base=$$(basename "$$in"); \
		out="test/out/$$base"; \
		if [ ! -f "$$out" ]; then \
			echo "SKIP $$base (Tidak ada output)"; \
			continue; \
		fi; \
		tmp=$$(mktemp); \
		if java -cp $(BIN_DIR) $(MAIN_CLASS) < "$$in" > "$$tmp"; then \
			if diff -u "$$out" "$$tmp" > /dev/null; then \
				echo "BERHASIL $$base"; pass=$$((pass+1)); \
			else \
				echo "GAGAL $$base"; \
				diff -u "$$out" "$$tmp" || true; \
				fail=$$((fail+1)); \
			fi; \
		else \
			echo "EROR $$base (Runtime)"; fail=$$((fail+1)); \
		fi; \
		rm -f "$$tmp"; \
	done; \
	echo "---"; \
	echo "Berhasil: $$pass  Gagal: $$fail"; \
	[ $$fail -eq 0 ]

# Hapus bin/
clean:
	rm -rf $(BIN_DIR)
