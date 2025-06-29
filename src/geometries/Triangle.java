package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

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

    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        // First, check if ray intersects the plane of the triangle
        // Use the old findIntersections method from plane as suggested in the instructions
        List<Point> planeIntersections = plane.findIntersections(ray);

        // If no intersection with plane, then no intersection with triangle
        if (planeIntersections == null) {
            return null;
        }

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        // Get the three vertices of the triangle
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        // Calculate vectors from ray origin to vertices
        Vector v1, v2, v3;
        try {
            v1 = p1.subtract(p0);
            v2 = p2.subtract(p0);
            v3 = p3.subtract(p0);
        } catch (IllegalArgumentException e) {
            // Ray starts at one of the vertices
            return null;
        }

        // Calculate normals for each edge using cross products
        Vector n1, n2, n3;
        try {
            // n1 = (v1 x v2) normalized
            n1 = v1.crossProduct(v2).normalize();
            // n2 = (v2 x v3) normalized
            n2 = v2.crossProduct(v3).normalize();
            // n3 = (v3 x v1) normalized
            n3 = v3.crossProduct(v1).normalize();
        } catch (IllegalArgumentException e) {
            // Vectors are parallel or zero - point is on edge/vertex
            return null;
        }

        // Check if all dot products have the same sign
        double vn1 = v.dotProduct(n1);
        double vn2 = v.dotProduct(n2);
        double vn3 = v.dotProduct(n3);

        // If any dot product is zero, the point is on edge/vertex
        if (isZero(vn1) || isZero(vn2) || isZero(vn3)) {
            return null;
        }

        // Check if all have the same sign
        if ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0)) {
            // Return intersection object with this triangle as the geometry
            return List.of(new Intersection(this, planeIntersections.get(0)));
        }

        return null;
    }
}