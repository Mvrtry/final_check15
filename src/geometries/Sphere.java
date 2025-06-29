package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class Sphere is the basic class representing a sphere in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere
     */
    private final Point center;

    /**
     * Constructor to initialize Sphere with center and radius
     *
     * @param center the center point
     * @param radius the radius value
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        // If ray starts at sphere center
        if (p0.equals(center)) {
            Point p = ray.getPoint(radius);
            return List.of(new Intersection(this, p));
        }

        Vector u = center.subtract(p0);
        double tm = alignZero(v.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        // No intersections: the ray's line is outside the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // Check if both points are behind the ray
        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(new Intersection(this, p1), new Intersection(this, p2));
        }

        // Check if only one point is in front of the ray
        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(new Intersection(this, p1));
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(new Intersection(this, p2));
        }

        // Both points are behind the ray
        return null;
    }
}