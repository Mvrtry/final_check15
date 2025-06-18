package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }
}