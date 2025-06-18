package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }
}