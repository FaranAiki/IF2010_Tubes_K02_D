package nabla.game.controller;

import nabla.game.GameLoop;
import nabla.model.entity.Game;
import nabla.type.GameState;
import nabla.ui.IRenderer;

/**
 * Base class untuk controller implementations.
 * <p>
 * <b>Prinsip</b> Template Method dengan menyediakan common logic,
 * subclass implementasikan handleInput spesifik platform.
 */
public abstract class AbstractController implements IController {
    protected final Game game;
    protected final GameLoop gameLoop;
    protected final IRenderer renderer;
    protected boolean running = true;

    protected AbstractController(Game game, GameLoop gameLoop, IRenderer renderer) {
        this.game = game;
        this.gameLoop = gameLoop;
        this.renderer = renderer;
    }

    @Override
    public final void run() {
        gameLoop.start();
        onStart();

        while (running) {
            GameState state = game.getGameState();

            switch (state) {
                case MENU -> handleMenu();
                case PLAYING -> handlePlaying();
                case PAUSED -> handlePaused();
                case WIN, LOSE -> handleGameOver(state);
            }

            sleepFrame();
        }

        shutdown();
    }

    @Override
    public final void stop() {
        running = false;
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
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running = false;
        }
    }

    /** Cleanup saat shutdown. */
    protected void shutdown() {
        gameLoop.stop();
        renderer.renderExitMessage();
    }
}
