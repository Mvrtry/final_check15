package primitives;

/**
 * Class Vector is the basic class representing a vector of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Vector extends Point {
    /**
     * X axis unit vector (1,0,0)
     */
    public static final Vector AXIS_X = new Vector(1, 0, 0);

    /**
     * Y axis unit vector (0,1,0)
     */
    public static final Vector AXIS_Y = new Vector(0, 1, 0);

    /**
     * Z axis unit vector (0,0,1)
     */
    public static final Vector AXIS_Z = new Vector(0, 0, 1);

    /**
     * Constructor to initialize Vector based on three coordinate values
     *
     * @param x first coordinate value
     * @param y second coordinate value
     * @param z third coordinate value
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Constructor to initialize Vector based on Double3 object
     *
     * @param xyz Double3 object containing all three coordinates
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }

    /**
     * Get the xyz coordinates
     *
     * @return Double3 object with coordinates
     */
    public Double3 getXyz() {
        return xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector other) && super.equals(other);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

    /**
     * Adds another vector to this vector
     *
     * @param other the other vector to add
     * @return new vector resulting from the addition
     */
    public Vector add(Vector other) {
        return new Vector(xyz.add(other.xyz));
    }

    /**
     * Subtracts another vector from this vector
     *
     * @param other the other vector to subtract
     * @return new vector resulting from the subtraction
     * @throws IllegalArgumentException if result is zero vector
     */
    public Vector subtract(Vector other) {
        return new Vector(xyz.subtract(other.xyz));
    }

    /**
     * Scales this vector by a scalar value
     *
     * @param scalar the scalar value
     * @return new scaled vector
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * Calculates the dot product with another vector
     *
     * @param other the other vector
     * @return dot product value
     */
    public double dotProduct(Vector other) {
        return xyz.d1() * other.xyz.d1() +
                xyz.d2() * other.xyz.d2() +
                xyz.d3() * other.xyz.d3();
    }

    /**
     * Calculates the cross product with another vector
     *
     * @param other the other vector
     * @return new vector resulting from the cross product
     */
    public Vector crossProduct(Vector other) {
        return new Vector(
                xyz.d2() * other.xyz.d3() - xyz.d3() * other.xyz.d2(),
                xyz.d3() * other.xyz.d1() - xyz.d1() * other.xyz.d3(),
                xyz.d1() * other.xyz.d2() - xyz.d2() * other.xyz.d1()
        );
    }

    /**
     * Calculates the squared length of the vector
     *
     * @return squared length of the vector
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculates the length of the vector
     *
     * @return length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes this vector (returns a new vector in the same direction with length 1)
     *
     * @return new normalized vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }
}