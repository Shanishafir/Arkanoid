package game.environment.sprite;

import biuoop.DrawSurface;

/**
 * Sprite Interface.
 */
public interface Sprite {

    /**
     * draws sprite on gui.
     * @param d drawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
