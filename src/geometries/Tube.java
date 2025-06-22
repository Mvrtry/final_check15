package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Class Tube is the basic class representing a tube in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Tube extends RadialGeometry {

    /**
     * The axis ray of the tube
     */
    protected final Ray axisRay;

    /**
     * Constructor to initialize Tube with axis ray and radius
     *
     * @param axisRay the axis ray
     * @param radius  the radius value
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        // Find the projection of point onto the axis
        Vector pointToAxisStart = point.subtract(axisRay.getP0());
        double projectionLength = pointToAxisStart.dotProduct(axisRay.getDir());

        // Special case: if the point projection coincides with ray start point
        if (isZero(projectionLength)) {
            return pointToAxisStart.normalize();
        }

        Point projectionPoint = axisRay.getP0().add(axisRay.getDir().scale(projectionLength));

        // The normal is from the projection point to the given point
        return point.subtract(projectionPoint).normalize();
    }
}