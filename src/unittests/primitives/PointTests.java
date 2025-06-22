package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for primitives.Point class
 * @author Maor Atari
 */
class PointTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /** Test method for {@link primitives.Point#add(primitives.Vector)}. */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple addition test
        Point p1 = new Point(1, 2, 3);
        Vector v1 = new Vector(2, 3, 4);
        Point expected = new Point(3, 5, 7);
        assertEquals(expected, p1.add(v1), "Point + Vector does not work correctly");

        // TC02: Addition with negative vector
        Vector v2 = new Vector(-1, -2, -3);
        Point expected2 = new Point(0, 0, 0);
        assertEquals(expected2, p1.add(v2), "Point + negative Vector does not work correctly");
    }

    /** Test method for {@link primitives.Point#subtract(primitives.Point)}. */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple subtraction test
        Point p1 = new Point(3, 5, 7);
        Point p2 = new Point(1, 2, 3);
        Vector expected = new Vector(2, 3, 4);
        assertEquals(expected, p1.subtract(p2), "Point - Point does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC11: Subtracting point from itself should throw exception
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "Point - itself does not throw an exception");
    }

    /** Test method for {@link primitives.Point#distanceSquared(primitives.Point)}. */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Distance squared between different points
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 6, 3);
        assertEquals(25, p1.distanceSquared(p2), DELTA, "Distance squared calculation is wrong");

        // =============== Boundary Values Tests ==================

        // TC11: Distance squared from point to itself
        assertEquals(0, p1.distanceSquared(p1), DELTA, "Distance squared from point to itself is not zero");
    }

    /** Test method for {@link primitives.Point#distance(primitives.Point)}. */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Distance between different points
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 6, 3);
        assertEquals(5, p1.distance(p2), DELTA, "Distance calculation is wrong");

        // =============== Boundary Values Tests ==================

        // TC11: Distance from point to itself
        assertEquals(0, p1.distance(p1), DELTA, "Distance from point to itself is not zero");
    }
}