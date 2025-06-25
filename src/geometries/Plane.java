package geometries;

import primitives.Point;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Class Plane is the basic class representing a plane in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Plane extends Geometry {
    /**
     * A point on the plane
     */
    private final Point q0;
    /**
     * The normal vector to the plane
     */
    private final Vector normal;

    /**
     * Constructor to initialize Plane based on three points
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     * @throws IllegalArgumentException if points are identical or collinear
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;

        try {
            Vector v1 = p2.subtract(p1);
            Vector v2 = p3.subtract(p1);
            normal = v1.crossProduct(v2).normalize();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Points cannot be identical or collinear");
        }
    }

    /**
     * Constructor to initialize Plane based on a point and normal vector
     *
     * @param q0     point on the plane
     * @param normal normal vector to the plane (will be normalized)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}