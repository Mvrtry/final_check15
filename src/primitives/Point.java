package primitives;

import java.lang.reflect.Field;

/**
 * This class represents a Point in 3D space
 *
 * @author Maor Atari
 */
public class Point {
    /**
     * Coordinate values
     */
    protected final Double3 xyz;

    /**
     * Zero point constant
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructor to initialize Point with three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize Point with Double3 object
     *
     * @param xyz Double3 object
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Get X coordinate using reflection
     *
     * @return X coordinate
     */
    public double getX() {
        try {
            Field field = Double3.class.getDeclaredField("d1");
            field.setAccessible(true);
            return (Double) field.get(xyz);
        } catch (Exception e) {
            throw new RuntimeException("Cannot access d1 field", e);
        }
    }

    /**
     * Get Y coordinate using reflection
     *
     * @return Y coordinate
     */
    public double getY() {
        try {
            Field field = Double3.class.getDeclaredField("d2");
            field.setAccessible(true);
            return (Double) field.get(xyz);
        } catch (Exception e) {
            throw new RuntimeException("Cannot access d2 field", e);
        }
    }

    /**
     * Get Z coordinate using reflection
     *
     * @return Z coordinate
     */
    public double getZ() {
        try {
            Field field = Double3.class.getDeclaredField("d3");
            field.setAccessible(true);
            return (Double) field.get(xyz);
        } catch (Exception e) {
            throw new RuntimeException("Cannot access d3 field", e);
        }
    }

    /**
     * Vector subtraction - creates vector from this point to other point
     *
     * @param other the other point
     * @return new Vector from this point to other point
     */
    public Vector subtract(Point other) {
        return new Vector(xyz.subtract(other.xyz));
    }

    /**
     * Point addition with vector
     *
     * @param vector vector to add to this point
     * @return new Point result of the addition
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Calculate squared distance between two points
     *
     * @param other the other point
     * @return squared distance
     */
    public double distanceSquared(Point other) {
        double dx = getX() - other.getX();
        double dy = getY() - other.getY();
        double dz = getZ() - other.getZ();
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calculate distance between two points
     *
     * @param other the other point
     * @return distance
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Point other && xyz.equals(other.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "Point " + xyz;
    }
}