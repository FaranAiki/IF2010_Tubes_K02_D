SRC_DIR := src
BIN_DIR := bin
MAIN_CLASS := nabla.Main

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

.PHONY: all build run run-cli run-swing run-javafx clean test

all: build

# Hanya untuk check build saja
build:
	@mkdir -p $(BIN_DIR)
	javac -cp lib/lanterna-3.1.3.jar -d $(BIN_DIR) $(SOURCES)

# Default: jalankan dengan UI JavaFX
run: run-javafx

run-cli: build
	java -cp $(BIN_DIR):lib/lanterna-3.1.3.jar nabla.Main cli

run-swing: build
	java -cp $(BIN_DIR) nabla.Main swing

run-javafx: build
	java -cp $(BIN_DIR) nabla.Main javafx

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
		if java -cp $(BIN_DIR):lib/lanterna-3.1.3.jar nabla.Main cli < "$$in" > "$$tmp"; then \
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
