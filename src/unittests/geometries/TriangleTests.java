package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Test method for {@link Triangle#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(-1, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Inside triangle
        List<Point> result = triangle.findIntersections(new Ray(new Point(0, 0.3, 0), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect triangle");
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0, 0.3, 1), result.get(0), "Ray intersection point is wrong");

        // TC02: Outside against edge
        assertNull(triangle.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1))),
                "Ray's line outside against edge");

        // TC03: Outside against vertex
        assertNull(triangle.findIntersections(new Ray(new Point(2, 2, 0), new Vector(0, 0, 1))),
                "Ray's line outside against vertex");

        // =============== Boundary Values Tests ==================
        // TC11: On edge
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0.5, 0), new Vector(0, 0, 1))),
                "Ray's line on edge");

        // TC12: In vertex
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, 1))),
                "Ray's line in vertex");

        // TC13: On edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point(2, -1, 0), new Vector(0, 0, 1))),
                "Ray's line on edge's continuation");

        // Additional boundary tests
        // TC14: Ray parallel to triangle
        assertNull(triangle.findIntersections(new Ray(new Point(1, 1, 2), new Vector(1, 0, 0))),
                "Ray parallel to triangle");

        // TC15: Ray starts at triangle plane but outside triangle
        assertNull(triangle.findIntersections(new Ray(new Point(3, 3, 1), new Vector(1, 0, 0))),
                "Ray starts at triangle plane but outside");
    }
}