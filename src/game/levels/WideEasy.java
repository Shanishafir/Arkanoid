package game.levels;

import game.LevelInformation;
import game.environment.collidable.Block;
import game.environment.sprite.Sprite;
import game.environment.sprite.Velocity;
import game.environment.sprite.backgrounds.BackgroundWideEasy;
import game.geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy class.
 */
public class WideEasy implements LevelInformation {
    private BackgroundWideEasy background = new BackgroundWideEasy();

    /**
     * This method returns the number of balls in the level.
     *
     * @return num of balls
     */
    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    /**
     * This method returns the initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     *
     * @return initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(Velocity.fromAngleAndSpeed(180, 5));
        return velocityList;
    }

    /**
     * This method returns the paddle's speed.
     *
     * @return paddle's speed
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * This method returns the paddle's width.
     *
     * @return paddle's width
     */
    @Override
    public int paddleWidth() {
        return 400;
    }

    /**
     * This method returns the level name.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return new String("Wide Easy");
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background of the level
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location
     *
     * @return The Blocks that make up this leve
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double widthOfBlock = 76;
        Color[] colorArr = {new Color(230, 50, 0), new  Color(230, 130, 0), Color.ORANGE };
        Color[] colorArr2 = {Color.BLUE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                blocks.add(new Block(new Point(20 + widthOfBlock * i, 250), widthOfBlock, 30, colorArr2[i % 3]));
            } else {
                blocks.add(new Block(new Point(20 + widthOfBlock * i, 250), widthOfBlock, 30, colorArr[i % 3]));
            }
        }

        return blocks;
    }

    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * this number should be <= blocks.size();
     *
     * @return Number of blocks that should be removed
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
