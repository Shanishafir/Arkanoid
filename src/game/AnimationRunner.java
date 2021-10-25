package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * This class runs the game.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor, builds the Animation Runner object.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * This method runs the game.
     * @param animation the animation is running
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * This method gives the key board sensor of the gui.
     * @return the key board sensor
     */
    public KeyboardSensor getKeyBoardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * exits the GUI.
     */
    public void closeGUI() {
        gui.close();
    }
}
