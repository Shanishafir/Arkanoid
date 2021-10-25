package game.environment.sprite;

import game.geometry.Point;

/**
 * Velocity Class.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx the delta of units that the ball does in x coordinate.
     * @param dy the delta of units that the ball does in y coordinate.
     */

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * gets x value of velocity.
     * @return double x
     */
    public double getDXVel() {
        return this.dx;
    }

    /**
     * sets new dx value.
     * @param newDX new desired velocity
     */
    public void setDX(double newDX) {
        this.dx = newDX;
    }
    /**
     * gets y value of velocity.
     * @return double y
     */
    public double getDYVel() {
        return this.dy;
    }
    /**
     * sets new dy value.
     * @param newDY new desired velocity
     */
    public void setDY(double newDY) {
        this.dy = newDY;
    }

    /**
     * Create velocity object from angle and speed.
     *
     * @param angle the angle
     * @param speed the speed
     * @return new velocity with new angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Replace the angle from degree to radians in order to use Math
        double radianAngle = Math.toRadians(270 + angle);
        double dx = speed * Math.cos(radianAngle);
        double dy = speed * Math.sin(radianAngle);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point.
     *
     * @param p The point
     * @return new point after the calculate, with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}