package game.environment.collidable;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.environment.sprite.Sprite;
import game.environment.sprite.Velocity;
import game.geometry.Ball;
import game.geometry.Point;
import game.geometry.Rectangle;

import java.awt.Color;

/**
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;
    private int paddleSpeed;

    /**
     * constructor method.
     * @param shape rectangle
     * @param keyboard keyboardSensor
     * @param color color
     * @param paddleSpeed the speed of paddle
     */
    public Paddle(Rectangle shape, KeyboardSensor keyboard, Color color, int paddleSpeed) {
        this.shape = shape;
        this.keyboard = keyboard;
        this.color = color;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * moves the paddle to the left.
     */
    public void moveLeft() {
        if (this.shape.getUpperLeft().getX() - this.paddleSpeed >= 20) { // check if hits border block
            Point newUpperLeft = new Point(this.shape.getUpperLeft().getX() - this.paddleSpeed,
                    this.shape.getUpperLeft().getY());
            this.shape = new Rectangle(newUpperLeft, this.shape.getWidth(), this.shape.getHeight());
        }
    }

    /**
     * moves he paddle to the right.
     */
    public void moveRight() {
        if (this.shape.getUpperLeft().getX() + this.paddleSpeed + this.shape.getWidth() <= 780) {
            Point newUpperLeft = new Point(this.shape.getUpperLeft().getX() + this.paddleSpeed,
                    this.shape.getUpperLeft().getY());
            this.shape = new Rectangle(newUpperLeft, this.shape.getWidth(), this.shape.getHeight());
        }
    }

    /**
     * moves paddle in response to the user input.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draws the paddle on the surface.
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    /**
     * returns collision shape.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * calculates velocity after hit.
     * @param collisionPoint point of collision
     * @param currentVelocity current velocity of colliding object
     * @param hitter the ball that hit the block
     * @return new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = this.shape.getUpperLeft().getX();
        double w = this.shape.getWidth();
        if (collisionPoint.getY() == this.shape.getUpperLeft().getY()) {
            for (int i = 1; i <= 5; i++) {
                if (i * (w / 5) >= this.shape.getUpperLeft().distance(collisionPoint)) {
                    return derivedVelocity(i, currentVelocity);
                }
            }
        }
        return new Velocity(-currentVelocity.getDXVel(), currentVelocity.getDYVel());
    }

    /**
     * checks on which of the 5 paddle areas, the collision occurs.
     * @param index area where collision occurs
     * @param currentVelocity current velocity of ball
     * @return new velocity value
     */
    private Velocity derivedVelocity(int index, Velocity currentVelocity) {
        if (index == 1) {
            return Velocity.fromAngleAndSpeed(300, 10);
        } else if (index == 2) {
            return Velocity.fromAngleAndSpeed(330, 10);
        } else if (index == 3) {
            return new Velocity(currentVelocity.getDXVel(), -currentVelocity.getDYVel());
        } else if (index == 4) {
            return Velocity.fromAngleAndSpeed(30, 10);
        } else {
            return Velocity.fromAngleAndSpeed(60, 10);
        }
    }

    /**
     * adds this paddle to the game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);

    }
}
