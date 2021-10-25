package game.environment.sprite.backgrounds;

import biuoop.DrawSurface;
import game.environment.sprite.Sprite;

import java.awt.Color;

/**
 * Creates the level's background.
 */
public class BackgroundDirectHit implements Sprite {
    /**
     * draws sprite on gui.
     *
     * @param d drawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(20, 20, 760, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 170, 70);
        d.drawCircle(400, 170, 100);
        d.drawCircle(400, 170, 130);
        d.drawLine(400, 40,  400, 320);
        d.drawLine(220, 170, 570, 170);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
