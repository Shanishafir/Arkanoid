package game.environment.collidable;
import game.GameLevel;
import game.geometry.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count.
 * of the number of blocks that remain
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor of this class. build new Block remover object.
     * @param game we removes blocks from
     * @param removedBlocks the counter of remained blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
     this.game = game;
     this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed.
     * from the game.and remove this listener from the block
     * that is being removed from the game
     * @param beingHit the block that hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
