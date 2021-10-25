package game.environment.collidable;

import game.environment.sprite.Velocity;
import game.geometry.Ball;
import game.geometry.Point;
import game.geometry.Rectangle;

/**
 * Collidable interface.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * changes velocity after hit occurs.
     * @param collisionPoint point of collision
     * @param currentVelocity current velocity of colliding object
     * @param hitter the ball that hit the block
     * @return new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

