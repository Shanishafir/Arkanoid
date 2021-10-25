package game;

import biuoop.DrawSurface;

/**
 * This interface of Animation.
 */
public interface Animation {
    /**
     * This method do one frame.
     * @param d the surface we draw the frame on
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method indicates when to stop the game.
     * @return if stop the game or not.
     */
    boolean shouldStop();
}
