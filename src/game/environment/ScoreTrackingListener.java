package game.environment;

import game.environment.collidable.Block;
import game.environment.collidable.Counter;
import game.environment.collidable.HitListener;
import game.geometry.Ball;

/**
 * This class tracking after the scores.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * This method gets a score counter to track after.
     * @param scoreCounter the score the player has
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method knows when a hit happen and increase the score accordingly.
     * @param beingHit the block that hit
     * @param hitter the ball that hit the block
     */

    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
       beingHit.removeHitListener(this);
    }
}
