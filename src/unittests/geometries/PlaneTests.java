package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 *
 * @author Maor Atari
 */
class PlaneTests {
    /**
     * Default constructor for PlaneTests
     */
    public PlaneTests() {
    }

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct plane construction with three non-collinear points
        assertDoesNotThrow(() -> new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Failed constructing a correct plane");

        // =============== Boundary Values Tests ==================

        // TC11: First and second points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 0, 1)),
                "Constructed a plane with identical first and second points");

        // TC12: First and third points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0)),
                "Constructed a plane with identical first and third points");

        // TC13: Second and third points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a plane with identical second and third points");

        // TC14: All three points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)),
                "Constructed a plane with all identical points");

        // TC15: All three points are collinear
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)),
                "Constructed a plane with collinear points");
    }

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test normal vector calculation
        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        Vector normal = plane.getNormal(new Point(0, 0, 0));

        // Ensure the result is a unit vector
        assertEquals(1, normal.length(), DELTA, "Plane's normal is not a unit vector");

        // Ensure the result is orthogonal to vectors in the plane
        Vector v1 = new Vector(-1, 1, 0); // from point1 to point2
        Vector v2 = new Vector(-1, 0, 1); // from point1 to point3

        assertEquals(0, normal.dotProduct(v1), DELTA,
                "Plane's normal is not orthogonal to first vector in plane");
        assertEquals(0, normal.dotProduct(v2), DELTA,
                "Plane's normal is not orthogonal to second vector in plane");
    }
}