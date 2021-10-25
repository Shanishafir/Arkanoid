package game.geometry; //ID : 207071721

/**
 * This class represents a point.
 */
public class Point {

    private double x;
    private double y;

    /**
     * constructor method.
     *
     * @param x is the x coordinate of the point
     * @param y is the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of thus point to the other point.
     *
     * @param other Point
     * @return the distance between two points - Point from Other
     */
    public double distance(Point other) {
        double distancePoints;
        distancePoints = Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
        return distancePoints;
    }

    /**
     * This method -- return true if the points are equal, false otherwise.
     *
     * @param other Point;
     * @return true or false.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        } else {
            return (this.x == other.x) && (this.y == other.y);
        }
    }

    /**
     * This method returns the x  value of this point.
     *
     * @return x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method returns the y  value of this point.
     *
     * @return y value of the point.
     */

    public double getY() {
        return this.y;
    }
}

