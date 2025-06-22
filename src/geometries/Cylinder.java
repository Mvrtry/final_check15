package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Class Cylinder is the basic class representing a cylinder in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Cylinder extends Tube {
    /**
     * The height of the cylinder
     */
    private final double height;

    /**
     * Constructor to initialize Cylinder with axis ray, radius and height
     *
     * @param axisRay the axis ray
     * @param radius  the radius value
     * @param height  the height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        // Calculate projection of point onto axis
        Vector pointToAxisStart = point.subtract(p0);
        double projectionLength = pointToAxisStart.dotProduct(dir);

        // Check if point is on bottom base (at t=0 or below)
        if (isZero(projectionLength) || projectionLength < 0) {
            return dir.scale(-1); // Normal pointing downward
        }

        // Check if point is on top base (at t=height or above)
        if (isZero(projectionLength - height) || projectionLength > height) {
            return dir; // Normal pointing upward
        }

        // Point is on the curved surface - use parent class implementation
        return super.getNormal(point);
    }
}