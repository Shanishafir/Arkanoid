package game.environment.collidable;

import game.geometry.Ball;

/**
 * This interface has objects that wants to be notified of hit events from HitNotifier.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
