package game.environment.sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import game.environment.collidable.Counter;

import java.awt.Color;

/**
 * This class is a indicator of score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Creates new score indicatore.
     *
     * @param score the indicator of score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * This method adds this sprite to game.
     * @param game the game is running
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * draws sprite on gui.
     *
     * @param d drawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = 0;
        int y = 0;
        int width = 800;
        int height = 20;
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawText(width / 2, height - 5, "Score: " + this.score.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
