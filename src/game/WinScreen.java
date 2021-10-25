package game;

import biuoop.DrawSurface;
import game.environment.collidable.Counter;
import java.awt.Color;

/**
 * WinScreen class.
 */
public class WinScreen implements Animation {
    private boolean stop;
    private Counter scoreCounter;
    /**
     * The constructor, gets keyboard sensor and make the game running.
     *
     * @param score counter
     */
    public WinScreen(Counter score) {
        this.stop = false;
        this.scoreCounter = score;
    }

    /**
     * Draws a losing screen.
     *
     * @param d the surface we draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(48, 27, 63));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(180, 165, 165));
        d.drawText(100, d.getHeight() / 2, "You Win! Your score is " + this.scoreCounter.getValue(), 35);
    }

    /**
     * This method stops and resumes the game.
     *
     * @return if stop or continue
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

