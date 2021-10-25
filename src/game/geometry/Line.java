package game.geometry; //ID: 207071721

import java.util.List;

/**
 * this class creates line object.
 */
public class Line {
    public static final double EPSILON = Math.pow(10, -14);
    private Point start;
    private Point end;
    private double b; //the intersection with X axis
    private boolean isVertical;
    private boolean isPoint;

    private double slope; //the slope of the line

    /**
     * this method getting two points and creates lone object.
     *
     * @param start is the first point of the line
     * @param end   is the last point of the line
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.start = start;
            this.end = end;
        } else {
            this.end = start;
            this.start = end;
        }

        // checks if the line is vertical.
        if (start.getX() - end.getX() == 0) {
            this.isVertical = true;
            this.slope = 0; //if vertical there is no slope
            this.b = 0; //if vertical there is no intersection with X
        } else {  //finds the slope and the intersection with X (b) if the line is not vertical
            this.isVertical = false;
            this.slope = ((start.getY() - end.getY()) / (start.getX() - end.getX()));
            this.b = (start.getY() - this.slope * start.getX());
        }
        isPoint =  (this.start.equals(this.end()));

    }
    /**
     * this method getting two points and creates lone object.
     *
     * @param start is the first point of the line.
     * @param end   is the last point of the line.
     * @param num int
     */
    public Line(Point start, Point end, int num) {
        this.start = start;
        this.end = end;
        // checks if the line is vertical.
        if (start.getX() - end.getX() == 0) {
            this.isVertical = true;
            this.slope = 0; //if vertical there is no slope
            this.b = 0; //if vertical there is no intersection with X
        } else {  //finds the slope and the intersection with X (b) if the line is not vertical
            this.isVertical = false;
            this.slope = ((start.getY() - end.getY()) / (start.getX() - end.getX()));
            this.b = (start.getY() - this.slope * start.getX());
        }
        isPoint =  (this.start.equals(this.end()));

    }

    /**
     * this method creates a line by getting 4 points.
     *
     * @param x1 is the x coordinate of the start point os the line.
     * @param y1 is the y coordinate of the start point os the line.
     * @param x2 is the x coordinate of the end point os the line.
     * @param y2 is the x coordinate of the end point os the line.
     */

    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
        //checks if the the line is vertical
        if (x1 - x2 == 0) {
            this.isVertical = true;
            this.slope = 0;
            this.b = 0;
        } else { //finds the slope if the line is not vertical
            this.isVertical = false;
            this.slope = ((y1 - y2) / (x1 - x2));
            this.b = y1 - this.slope * x1;
        }
    }

    /**
     * checks if this line is a point.
     * @return true if is point
     */
    public boolean checkPoint() {
        return this.isPoint;
    }

    /**
     * this method gives the x intersection point with y coordinate.
     *
     * @return b  is the x x intersection point with y coordinate.
     */
    public double getB() {
        return this.b;
    }

    /**
     * defines length to be the distance between the start and the end of the line.
     * sent this two points to method that calculates the distance.
     *
     * @return length  the length of the line.
     */
    public double length() {

        double length = this.start.distance(this.end);
        return length;
    }

    /**
     * find the middle point of the line.
     *
     * @return middle the middle point of the line/
     */
    public Point middle() {
        double middleX = ((start.getX() + end.getX()) / 2);
        double middleY = ((start.getY() + end.getY()) / 2);
        Point middlePoint = new Point(middleX, middleY);
        return middlePoint;
    }

    /**
     * Gets the start point of the line.
     *
     * @return start  the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gets the end point of the line.
     *
     * @return end  the start point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Return true if this line is intersecting with other line.
     * this method separates the two lines into different conditions.
     *
     * @param other another line.
     * @return true if they intersects, otherwise return false.
     */
    public boolean isIntersecting(Line other) {
        if (this.isVertical && other.isVertical) {
            return this.isIntersectionInVertical(other);
        } else if (!this.isVertical && !other.isVertical) {
            return this.isIntersectionUnVertical(other);
        } else { //only one line is Vertical
            return this.isIntersectionOneVertical(other);
        }
    }

    /**
     * this method checks if two lines that are vertical are intersecting.
     *
     * @param other another line
     * @return true if they are intersect, false otherwise.
     */
    public boolean isIntersectionInVertical(Line other) {
        if (this.equals(other)) {
            return false; //The segments are the same, there's no one intersection
        } else if (other.checkPoint()) {
            return this.isIntersectionOneVertical(other);
        } else {
            if (this.start.getX() == other.start.getX()) { //they have the same equation
                //they don't have one intersection
                return this.start.getY() == other.end.getY() || other.start.getY() == this.end.getY();
            } else {
                return false; //the lines are parallel, they don't have intersection
            }
        }

    }

    /**
     * this method checks if two lines that are not vertical are intersecting.
     *
     * @param other another line
     * @return true if they are intersect, false otherwise.
     */
    public boolean isIntersectionUnVertical(Line other) {
        if (this.slope == other.slope) {
            if (this.b != other.b) {
                return false; //the lines are parallel;
            } else { //the lines have the same equation
                return this.start == other.end || this.end == other.start;
            }
        } else {
            double xIntersection = roundCalc((other.b - this.b) / (this.slope - other.slope));
            double yIntersection = xIntersection * this.slope + this.b;
            //the intersection is in the boundary
            return (this.isPointOnLine(new Point(xIntersection, yIntersection))
                    && other.isPointOnLine(new Point(xIntersection, yIntersection)));
        }
    }

    /**
     * rounds the given number to the fourth decimal point.
     * @param num number
     * @return rounded number
     */
    private static double roundCalc(double num) {
        double numWithDecimal = num * 10000;
        if (Math.floor(numWithDecimal) % 10000 == 9999) {
            return Math.ceil(num);
        } else if (Math.floor(numWithDecimal) % 10000 == 0) {
            return Math.floor(num);
        }
        return num;
    }

    /**
     * this method checks if two lines that only one is vertical are intersecting.
     * @param other another line
     * @return true if they are intersect, false otherwise.
     */
    public boolean isIntersectionOneVertical(Line other) {
        double yIntersection;
        if (this.isVertical && !other.checkPoint()) {
            yIntersection = this.start().getX() * other.slope + other.getB();
            if (!this.checkPoint() && !other.checkPoint()) {
                return this.isPointOnLine(new Point(this.start().getX(), yIntersection))
                        && other.isPointOnLine(new Point(this.start().getX(), yIntersection));
            }
        } else if (other.isVertical && !this.checkPoint()) {
            yIntersection = other.start().getX() * this.slope + this.getB();
            if (!this.checkPoint() && !other.checkPoint()) {
                return this.isPointOnLine(new Point(other.start().getX(), yIntersection))
                        && other.isPointOnLine(new Point(other.start().getX(), yIntersection));
            }
        }

        if (this.checkPoint()) {
            return other.isPointOnLine(this.start);
        }
        return this.isPointOnLine(other.start());

    }

    /**
     * checks if the point is on line.
     * @param p point
     * @return true if on line
     */
    public boolean isPointOnLine(Point p) {
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinX = Math.min(this.start.getX(), this.end.getX());
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double x = p.getX();
        double y = p.getY();
        return (thisMinX <= x && thisMaxX >= x) && (thisMinY <= y && thisMaxY >= y);
    }

    /**
     * Finds the intersection point between two lines.
     *
     * @param other other line.
     * @return the intersection point. if there is no intersection return null.
     */
    public Point intersectionWith(Line other) {
        double xIntersection;
        double yIntersection;

        if (!this.isIntersecting(other)) { //there is no intersection
            return null;
        } else {
            if (!this.isVertical && !other.isVertical) { //this line  and other line aren't vertical.
                if (this.slope == other.slope) { //they intersect and have the same slope.so thy have same equitation.
                    if (this.end == other.start) {
                        return this.end;
                    } else {
                        return this.start;
                    }
                } else { //they are not vertical and don't have the same slope.
                    xIntersection = ((other.b - this.b) / (this.slope - other.slope));
                    yIntersection = this.slope * xIntersection + this.b;
                    Point intersectWith = new Point(xIntersection, yIntersection);
                    return intersectWith;
                }
            } else {
                if (this.isVertical && other.isVertical) { //they have the same equitation.
                    if (this.end == other.start) {
                        return this.end;
                    } else {
                        return this.start;
                    }
                } else {
                    if (this.isVertical) {
                        xIntersection = this.start.getX();
                        yIntersection = other.slope * xIntersection + other.b;
                        Point intersectionWith = new Point(xIntersection, yIntersection);
                        return intersectionWith;
                    } else {
                        xIntersection = other.start.getX();
                        yIntersection = this.slope * xIntersection + this.b;
                        Point intersectionWith = new Point(xIntersection, yIntersection);
                        return intersectionWith;
                    }
                }
            }
        }
    }


    /**
     * Checks if two lines are equal.
     *
     * @param other the other line we compare to
     * @return true if equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start == other.start && this.end == other.end() && this.slope == other.slope && this.b == other.b;
    }

    /**
     * return the closet intersection point to the start of the line.
     * if there is no intersection return null/
     *
     * @param rect the Rectangle we check the intersection with this line
     * @return Point the closet point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null || intersectionPoints.isEmpty()) {
            return null;
        } else {
            Point minPoint = intersectionPoints.get(0);
            for (int i = 0; i < intersectionPoints.size(); i++) {
                if (intersectionPoints.get(i).distance(this.start) < minPoint.distance(this.start)) {
                    minPoint = intersectionPoints.get(i);
                }
            }
            return minPoint;
        }
    }

}
