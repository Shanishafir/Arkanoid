package game.levels;

import game.LevelInformation;
import game.environment.collidable.Block;
import game.environment.sprite.Sprite;
import game.environment.sprite.Velocity;
import game.environment.sprite.backgrounds.BackgroundGreen3;
import game.geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Green3 class.
 */
public class Green3 implements LevelInformation {
    private BackgroundGreen3 background = new BackgroundGreen3();

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
        for (int i = 0; i < 2; i++) {
        velocityList.add(Velocity.fromAngleAndSpeed(180, 5));
        }
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
        return 150;
    }

    /**
     * This method returns the level name.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return new String("Green3");
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
               Color[] colorArr = {Color.GREEN, Color.PINK, new Color(0, 0, 250), Color.YELLOW, Color.RED, Color.GRAY};
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= i + 6; j++) {
                blocks.add(new Block(new Point(730 - (j - 1) * 50, 250 - (i - 1) * 30),
                        50, 30, colorArr[i - 1]));
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

