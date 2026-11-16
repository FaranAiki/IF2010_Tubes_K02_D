package nabla.controller;

import nabla.game.Game;
import nabla.type.GameState;

import nabla.singleton.Config;

/**
 * AbstractController untuk setiap tipe controller
 * TODO: apa IController hapus aja
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

    protected void onStart() {
    }

    protected void sleepFrame() {
        try {
            Thread.sleep(Config.SLEEPING_THREAD);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running = false;
        }
    }

    protected void shutdown() {
    }
}
