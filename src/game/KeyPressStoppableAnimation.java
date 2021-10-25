package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop = false;
    private  KeyboardSensor keyboard;
    private String keyName;
    private Animation animation1;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     * @param sensor KeyboardSensor
     * @param key string
     * @param animation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.keyName = key;
        this.animation1 = animation;
    }

    /**
     * This method do one frame.
     *
     * @param d the surface we draw the frame on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation1.doOneFrame(d);
        if (!this.keyboard.isPressed(keyName)) {
            isAlreadyPressed = false;
        }
        if (this.keyboard.isPressed(keyName) && !isAlreadyPressed) {
            this.stop = true;
        }
    }

    /**
     * This method indicates when to stop the game.
     *
     * @return if stop the game or not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}