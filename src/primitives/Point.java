package primitives;

/**
 * Class Point is the basic class representing a point of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Point {
    /**
     * Zero point (0,0,0)
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * The coordinates of the point
     */
    protected final Double3 xyz;

    /**
     * Constructor to initialize Point based on three coordinate values
     *
     * @param x first coordinate value
     * @param y second coordinate value
     * @param z third coordinate value
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize Point based on Double3 object
     *
     * @param xyz Double3 object containing all three coordinates
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "" + xyz;
    }

    /**
     * Adds a vector to this point
     *
     * @param vector the vector to add
     * @return new point after adding the vector
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Subtracts another point from this point
     *
     * @param other the other point to subtract
     * @return vector from other point to this point
     */
    public Vector subtract(Point other) {
        return new Vector(xyz.subtract(other.xyz));
    }

    /**
     * Calculates the squared distance between this point and another point
     *
     * @param other the other point
     * @return squared distance between the points
     */
    public double distanceSquared(Point other) {
        Double3 diff = xyz.subtract(other.xyz);
        return diff.d1() * diff.d1() + diff.d2() * diff.d2() + diff.d3() * diff.d3();
    }

    /**
     * Calculates the distance between this point and another point
     *
     * @param other the other point
     * @return distance between the points
     */
    public double distance(Point other) {

        return Math.sqrt(distanceSquared(other));
    }
}