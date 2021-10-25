package game;

import biuoop.DrawSurface;

/**
 * This class is to stop the game after the player pressed "p".
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * The constructor, gets keyboard sensor and make the game running.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * This method does one frame after the player pressed "p" to stop.
     * and continue the game after he press on the space
     * @param d the surface we draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * This method stop and continue the game.
     * @return if stop or continue
     */
    public boolean shouldStop() { return this.stop; }
}
