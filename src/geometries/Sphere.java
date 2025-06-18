package geometries;

import primitives.Point;
import primitives.Vector;

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
        return null;
    }
}