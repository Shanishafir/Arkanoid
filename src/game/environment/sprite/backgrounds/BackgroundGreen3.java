package game.environment.sprite.backgrounds;

import biuoop.DrawSurface;
import game.environment.sprite.Sprite;

import java.awt.Color;

/**
 * Creates the level's background.
 */
public class BackgroundGreen3 implements Sprite {
    /**
     * draws sprite on gui.
     *
     * @param d drawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(20, 100, 20));
        d.fillRectangle(20, 20, 800, 600);

    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
