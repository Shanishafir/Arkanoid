package game.environment.collidable;

import biuoop.DrawSurface;
import game.GameLevel;
import game.environment.sprite.Sprite;
import game.environment.sprite.Velocity;
import game.geometry.Ball;
import game.geometry.Line;
import game.geometry.Point;
import game.geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class creates all the collide able objects.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * create a collide rectangle object.
     *
     * @param upperLeft the upper left corner of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {

        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * adds block to game.
     *
     * @param game game object
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Gets the object the ball collide to.
     *
     * @return Rectangle is the only shape of the collide able objects
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Updating the velocity of the ball after the collision.
     *
     * @param collisionPoint  the intersection point between the ball and the block
     * @param currentVelocity the velocity of the ball that collision with this block
     * @param hitter the ball that hit the block
     * @return currentVelocity the updated velocity after collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        List<Line> linesOfBlock = rectangle.getLinesOfRectangle();
        Line verticalLineFromPoint = new Line(collisionPoint, collisionPoint); //consider the point as a vertical
        // line to make it easier
        if (linesOfBlock.get(0).isIntersecting(verticalLineFromPoint)
                || (linesOfBlock.get(2).isIntersecting(verticalLineFromPoint))) { //the collision with Horizontal line
            currentVelocity.setDY(-currentVelocity.getDYVel());
        } else if (linesOfBlock.get(1).isIntersecting(verticalLineFromPoint)
                || linesOfBlock.get(3).isIntersecting(verticalLineFromPoint)) { //the collision with vertical line
            currentVelocity.setDX(-currentVelocity.getDXVel());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * draws block rectangle on gui.
     *
     * @param surface draw surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int xUpperLeft = (int) this.rectangle.getUpperLeft().getX();
        int yUpperLeft = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(xUpperLeft, yUpperLeft, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(xUpperLeft, yUpperLeft, width, height);
    }

    /**
     * notifies block time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * This method removes this block from the game after the hit.
     * @param game the game we are playing and want to remove a block from it
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSpriteS(this);
    }

    /**
     * This method notify of a hit to the listeners.
     * @param hitter the ball that hits this block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add h1 as a listener to hit events.
     * @param hl the object we want to add to the listeners
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the object we want to remove from the listeners
     */
    @Override
   public void removeHitListener(HitListener hl) {
       this.hitListeners.remove(hl);
    }
}
