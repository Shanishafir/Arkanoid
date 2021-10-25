package game.environment.collidable;

import game.geometry.Point;

/**
 * CollisionInfo class.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param collisionPoint point of collision
     * @param collisionObject object of collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * gets point of collision.
     * @return collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * gets collision object.
     * @return collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }


}
