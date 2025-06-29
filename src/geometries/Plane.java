package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
     * Constructor to initialize Plane with a point and a normal vector
     *
     * @param q0     a point on the plane
     * @param normal the normal vector (will be normalized)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructor to initialize Plane based on three points
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     * @throws IllegalArgumentException if the points are on the same line
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p1; // Use first point as reference point

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Getter for the reference point
     *
     * @return the reference point of the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * Getter for the normal vector
     *
     * @return the normal vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        // Ray is defined by: P = P0 + t*v, t >= 0

        // Plane equation: N·(P-Q0) = 0
        // Substituting ray equation: N·(P0 + t*v - Q0) = 0
        // Solving for t: t = N·(Q0-P0) / N·v

        // Check if ray is parallel to plane
        double nv = normal.dotProduct(v);

        // Ray is parallel to plane - no intersections
        if (isZero(nv)) {
            return null;
        }

        // Check if P0 is on the plane
        Vector p0q0;
        try {
            p0q0 = q0.subtract(p0);
        } catch (IllegalArgumentException e) {
            // P0 equals Q0, meaning P0 is on the plane
            return null;
        }

        double t = alignZero(normal.dotProduct(p0q0) / nv);

        // Check if intersection is behind ray's head or at ray's head
        if (t <= 0) {
            return null;
        }

        // Calculate intersection point
        Point intersectionPoint = ray.getPoint(t);

        return List.of(new Intersection(this, intersectionPoint));
    }
}