package nabla.ui;

import nabla.game.Game;
import nabla.type.GameState;

/**
 * Base class untuk renderer implementations.
 * <p>
 * Renderer punya thread sendiri dan hanya fokus pada rendering state permainan
 * dari {@link Game}. Subclass hanya perlu mengimplementasikan method render-nya.
 */
public abstract class AbstractRenderer implements IRenderer {
    protected final Game game;
    protected volatile boolean running = false;
    private Thread thread;

    protected AbstractRenderer(Game game) {
        this.game = game;
    }

    @Override
    public final void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this::renderLoop, "Renderer-" + getClass().getSimpleName());
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
        renderExitMessage();
    }

    @Override
    public final boolean isRunning() {
        return running;
    }

    @Override
    public final Game getGame() {
        return game;
    }

    private void renderLoop() {
        while (running) {
            GameState state = game.getGameState();
            switch (state) {
                case MENU -> renderMenu();
                case HOW_TO_PLAY -> renderHowToPlay();
                case PAUSED -> renderPaused();
                case WIN, LOSE -> {
                    renderGameOver(state);
                    renderGameOverOptions();
                }
                default -> renderGame(game);
            }
            sleepFrame();
        }
    }

    private void sleepFrame() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running = false;
        }
    }
}
