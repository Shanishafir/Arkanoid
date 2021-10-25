package game;

import biuoop.KeyboardSensor;
import game.environment.collidable.Counter;

import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * constructor.
     *
     * @param ar Animation Runner
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * this method runs all the levels in the LevelInformation list.
     *
     * @param levels list
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean gameOver = false;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.score);
            level.initialize();
            level.run();
            if (level.getReamainNumBalls() <= 0) {
                gameOver = true;
                break;
            }
            //this.score.increase(level.getScore().getValue());

        }
        if (gameOver) {
            this.animationRunner.run(
                    new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, new LoseScreen(score))
            );
        } else {
            this.animationRunner.run(
                    new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, new WinScreen(score)));
        }
        animationRunner.closeGUI();
    }
}
