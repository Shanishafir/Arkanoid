package game.geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import game.environment.GameEnvironment;
import game.environment.collidable.Collidable;
import game.environment.collidable.CollisionInfo;
import game.environment.sprite.Sprite;
import game.environment.sprite.Velocity;

import java.awt.Color;


/**
 * creates ball.
 */
public class Ball implements Sprite {

    private final int r;
    private final Color color;
    private Point center;
    private Velocity v = new Velocity(0, 0); // initiation
    private GameEnvironment g;

    /**
     * constructor.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */

    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * adds ball to game object.
     *
     * @param game game object
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        this.setGameEnvironment(game.getEnvironment());
    }

    /**
     * constructor.
     * using x and y coordinate to create the center point and sent it to the firs constructor.
     *
     * @param xCenter the x coordinate of the point center of the ball.
     * @param yCenter the x coordinate of the point center of the ball.
     * @param r       the radius of the ball.
     * @param color   the color of the ball.
     */
    public Ball(int xCenter, int yCenter, int r, Color color) {
        this(new Point(xCenter, yCenter), r, color);
    }


    /**
     * Gives the x coordinate of the center.
     *
     * @return x coordinate of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gives the y coordinate of the center.
     *
     * @return y coordinate of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gives the size of the ball.
     *
     * @return r the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Changes the center of the ball to a new one.
     *
     * @param centerPoint the new center.
     */
    public void setCenter(Point centerPoint) {
        this.center = centerPoint;
    }

    /**
     * Gives the color of the ball.
     *
     * @return color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given surface.
     *
     * @param surface the one who's given.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * set the velocity of the ball.
     *
     * @param dx the delta units in x coordinate.
     * @param dy the delta units in y coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gives the velocity of the ball.
     *
     * @return v the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Set the velocity of the ball.
     *
     * @param velocity the velocity.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * Moves the ball one step. each ball with his velocity.
     * Makes sure that the ball doesn't cross the boundaries- xyMax,xyMin.
     */
    public void moveOneStep() {
        CollisionInfo info = this.g.getClosestCollision(this.trajectory());
        if (info == null) {
            this.setCenter(this.getVelocity().applyToPoint(this.center));
        } else {
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
            int numOfLine = this.checkOnWhichLine(info.collisionPoint(), info.collisionObject());
            if (numOfLine == 0) {
                this.setCenter(new Point(info.collisionPoint().getX(), info.collisionPoint().getY() - this.getSize()));
            } else if (numOfLine == 1) {
                this.setCenter(new Point(info.collisionPoint().getX() + this.getSize(), info.collisionPoint().getY()));
            } else if (numOfLine == 2) {
                this.setCenter(new Point(info.collisionPoint().getX(), info.collisionPoint().getY() + this.getSize()));
            } else {
                this.setCenter(new Point(info.collisionPoint().getX() - this.getSize(), info.collisionPoint().getY()));
            }
        }
    }

    /**
     * checks where on rectangle collision happens.
     *
     * @param collision point
     * @param colli     collidable object
     * @return index of line of collision rectangle
     */
    public int checkOnWhichLine(Point collision, Collidable colli) {
        Line collisionLine = new Line(collision, collision);
        for (int i = 0; i < colli.getCollisionRectangle().getLinesOfRectangle().size(); i++) {
            if (colli.getCollisionRectangle().getLinesOfRectangle().get(i).isIntersecting(collisionLine)) {
                return i;
            }
        }
        return -1; // default
    }

    /**
     * initialize game environment.
     *
     * @param game GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.g = game;
    }

    /**
     * returns trajectory of ball.
     *
     * @return trajectory as line
     */
    public Line trajectory() {
        double dx = this.getVelocity().getDXVel();
        double dy = this.getVelocity().getDYVel();
        Point endOfTrajectory = new Point(this.center.getX() + dx, this.center.getY() + dy);
        Line trajectory = new Line(this.center, endOfTrajectory, 1);
        return trajectory;
    }

    /**
     * moves ball one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     *Removes this ball from the game.
     * @param game the game is running right now
     */
    public void removeFromGame(GameLevel game) {
        game.removeSpriteS(this);
    }
}