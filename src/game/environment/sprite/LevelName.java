package game.environment.sprite;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * Draws the specific level name on the top on the board.
 */
public class LevelName implements Sprite {
private String name;

    /**
     * Constructor.
     * @param name the name of the level
     */
    public LevelName(String name) {
        this.name = name;
    }

    /**
     * Add this sprite to the game.
     * @param gameLevel the level is playing
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * draws sprite on gui.
     *
     * @param d drawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, 15, "Level Name : " + this.name, 16);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
