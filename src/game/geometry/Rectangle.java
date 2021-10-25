package game.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * this class creates a rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerRight;
    private Point lowerLeft;
    private double width;
    private double height;
    private List<Line> linesOfRectangle;


    /**
     * Create a new rectangle with location, height and width.
     *
     * @param upperLeft the location of thr upper left corner of the rectangle
     * @param width     of the rectangle
     * @param height    of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.lowerRight = new Point(this.lowerLeft.getX() + this.width, this.lowerLeft.getY());
        this.linesOfRectangle = new ArrayList<Line>(); //save the lines of the rectangle.
        Line a = new Line(this.upperLeft, upperRight);
        Line b = new Line(upperRight, lowerRight);
        Line c = new Line(lowerLeft, lowerRight);
        Line d = new Line(this.upperLeft, lowerLeft);
        linesOfRectangle.add(a);
        linesOfRectangle.add(b);
        linesOfRectangle.add(c);
        linesOfRectangle.add(d);

    }

    /**
     * find the intersection points between the rectangle and a line.
     *
     * @param line the line we check intersection with
     * @return intersectionPoints that is the list of all the intersections
     * this list can be empty
     */

    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (this.linesOfRectangle.get(i).isIntersecting(line)) {
                intersectionPoints.add(this.linesOfRectangle.get(i).intersectionWith(line));
            }
        }
        return intersectionPoints;
    }

    /**
     * Get the width of the rectangle.
     *
     * @return width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the rectangle.
     *
     * @return height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get the upper left point of the rectangle.
     *
     * @return upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Get the lines of the rectangle.
     *
     * @return LinesOfRectangle of the rectangle
     */
    public List<Line> getLinesOfRectangle() {
        return this.linesOfRectangle;
    }


}

