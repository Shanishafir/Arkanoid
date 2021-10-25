package game.geometry;

import game.GameLevel;
import game.environment.collidable.Block;
import game.environment.collidable.Counter;
import game.environment.collidable.HitListener;

/**
 * This class removes a ball after hitting the lower boundary block.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * A constructor for this class.
     * @param game the game is running
     * @param remainingBalls num of balls of the screen
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method removes a ball that hit the lower boundary block.
     * @param beingHit the block that hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);

    }
}
