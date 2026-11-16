package nabla.controller;

import nabla.game.Game;
import nabla.type.GameState;

import nabla.singleton.Config;

/**
 * Base class untuk controller implementations.
 * <p>
 * <b>Design Pattern Template Method</b> dengan menyediakan common logic,
 * subclass implementasikan handleInput spesifik platform.
 * <p>
 * Controller HANYA bergantung pada {@link Game} (Model). Tidak tahu-menahu
 * soal {@code GameLoop} maupun renderer. Thread controller dikelola oleh
 * {@link #start()}/{@link #stop()} secara internal.
 */
public abstract class AbstractController implements IController {
    protected final Game game;
    protected volatile boolean running = false;
    private Thread thread;

    protected AbstractController(Game game) {
        this.game = game;
    }

    @Override
    public final void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this::run, "Controller-" + getClass().getSimpleName());
        thread.start();
    }

    @Override
    public final void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        shutdown();
    }

    @Override
    public final boolean isRunning() {
        return running;
    }

    /**
     * Loop kontrol utama. Dijalankan di thread controller sendiri.
     * Dipanggil oleh {@link #start()}.
     */
    public final void run() {
        onStart();

        while (running) {
            GameState state = game.getGameState();

            switch (state) {
                case MENU -> handleMenu();
                case HOW_TO_PLAY -> handleHowToPlay();
                case PLAYING -> handlePlaying();
                case PAUSED -> handlePaused();
                case WIN, LOSE -> handleGameOver(state);
            }

            sleepFrame();
        }
    }

    @Override
    public final GameState getGameState() {
        return game.getGameState();
    }

    @Override
    public final void setGameState(GameState state) {
        game.setGameState(state);
    }

    /** Hook untuk initialization khusus subclass. */
    protected void onStart() {
        /* Default: no-op */
    }

    /** Handle menu state. */
    protected abstract void handleMenu();

    /** Handle how to play state. */
    protected abstract void handleHowToPlay();

    /** Handle playing state. */
    protected abstract void handlePlaying();

    /** Handle paused state. */
    protected abstract void handlePaused();

    /** Handle game over state. */
    protected abstract void handleGameOver(GameState state);

    /** Handle input pemain (spesifik platform). */
    @Override
    public abstract void handleInput(String input);

    /** Sleep frame untuk rate limiting. */
    protected void sleepFrame() {
        try {
            Thread.sleep(Config.SLEEPING_THREAD);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running = false;
        }
    }

    /** Cleanup saat shutdown. */
    protected void shutdown() {
        /* Default: no-op; subclass boleh override jika perlu cleanup spesifik */
    }
}
