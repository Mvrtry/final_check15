package geometries;

import primitives.Point;

/**
 * Class Triangle is the basic class representing a triangle in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Triangle extends Polygon {
    /**
     * Constructor to initialize Triangle based on three vertices
     *
     * @param p1 first vertex
     * @param p2 second vertex
     * @param p3 third vertex
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
}