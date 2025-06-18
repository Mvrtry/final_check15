package geometries;

/**
 * Abstract class RadialGeometry is the basic class representing radial geometries
 * (geometries with radius) in Cartesian 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * The radius of the geometry
     */
    protected final double radius;
    /**
     * The squared radius of the geometry (for optimization)
     */
    protected final double radiusSquared;

    /**
     * Constructor to initialize RadialGeometry with a radius
     *
     * @param radius the radius value
     */

    protected RadialGeometry(double radius) {
        this.radius = radius;
        radiusSquared = radius * radius;
    }
}