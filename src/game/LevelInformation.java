package game;

import game.environment.collidable.Block;
import game.environment.sprite.Sprite;
import game.environment.sprite.Velocity;

import java.util.List;

/**
 * This interface has the information of a level in the game.
 */
public interface LevelInformation {
    /**
     * This method returns the number of balls in the level.
     * @return num of balls
     */
    int numberOfBalls();
    /**
     * This method returns the initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     * @return initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method returns the paddle's speed.
     * @return paddle's speed
     */
    int paddleSpeed();
    /**
     * This method returns the paddle's width.
     * @return paddle's width
     */
    int paddleWidth();
    /**
     * This method returns the level name.
     * @return the level name
     */
    String levelName();
    /**
     *  Returns a sprite with the background of the level.
     * @return background of the level
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location
     * @return The Blocks that make up this leve
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * this number should be <= blocks.size();
     * @return Number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
