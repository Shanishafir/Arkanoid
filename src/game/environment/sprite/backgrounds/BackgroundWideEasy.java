package game.environment.sprite.backgrounds;

import biuoop.DrawSurface;
import game.environment.sprite.Sprite;

import java.awt.Color;

/**
 * Creates the level's background.
 */
public class BackgroundWideEasy implements Sprite {
    /**
     * draws sprite on gui.
     *
     * @param d drawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(20, 20, 800, 600);
        int counter = 0;
        d.setColor(new Color(240, 220, 160));
        for (int i = 0; i < 100; i++) {
            d.drawLine(120, 150, counter, 250);
            counter = counter + 7;
        }
        d.setColor((new Color(240, 220, 160)));
        d.fillCircle(120, 150, 70);
        d.setColor(new Color(220, 170, 40));
        d.fillCircle(120, 150, 60);
        d.setColor(Color.ORANGE);
        d.fillCircle(120, 150, 50);


    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
