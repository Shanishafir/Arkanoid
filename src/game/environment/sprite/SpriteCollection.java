package game.environment.sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class.
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites = new ArrayList<>();

    /**
     * adds sprite to this.
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        listOfSprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copyList = new ArrayList<>(this.listOfSprites);
        for (Sprite s : copyList) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copyList = new ArrayList<>(this.listOfSprites);
        for (Sprite s : copyList) {
            s.drawOn(d);
        }
    }

    /**
     * Removes sprite s from list of sprites.
     * @param s the sprite we remove
     */
    public void deleteSpriteS(Sprite s) {
        listOfSprites.remove(s);
    }
}
