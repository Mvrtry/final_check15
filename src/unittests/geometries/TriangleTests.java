package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for geometries.Triangle class
 *
 * @author Maor Atari
 */
class TriangleTests {
    /**
     * Default constructor for TriangleTests
     */
    public TriangleTests() {
    }

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test normal vector calculation for triangle
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        Vector normal = triangle.getNormal(new Point(0, 0, 0));

        // Ensure the result is a unit vector
        assertEquals(1, normal.length(), DELTA, "Triangle's normal is not a unit vector");

        // Ensure the result is orthogonal to the triangle's edges
        Vector edge1 = new Vector(-1, 1, 0); // from first to second vertex
        Vector edge2 = new Vector(-1, 0, 1); // from first to third vertex

        assertEquals(0, normal.dotProduct(edge1), DELTA,
                "Triangle's normal is not orthogonal to first edge");
        assertEquals(0, normal.dotProduct(edge2), DELTA,
                "Triangle's normal is not orthogonal to second edge");
    }
}