package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 *
 * @author Maor Atari
 */
class SphereTests {
    /**
     * Default constructor for SphereTests
     */
    public SphereTests() {
    }

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * A point used in some tests
     */
    private final Point p001 = new Point(0, 0, 1);
    /**
     * A point used in some tests
     */
    private final Point p100 = new Point(1, 0, 0);
    /**
     * A vector used in some tests
     */
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link Sphere#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test normal vector calculation for sphere
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);
        Vector normal = sphere.getNormal(new Point(1, 0, 0));

        // Ensure the result is a unit vector
        assertEquals(1, normal.length(), DELTA, "Sphere's normal is not a unit vector");

        // Ensure the normal points from center to the point
        Vector expected = new Vector(1, 0, 0);
        assertEquals(expected, normal, "Sphere's normal is incorrect");

        // TC02: Test normal vector at different point
        Vector normal2 = sphere.getNormal(new Point(0, 0, 1));
        Vector expected2 = new Vector(0, 0, 1);
        assertEquals(expected2, normal2, "Sphere's normal is incorrect at different point");
    }

    /**
     * Test method for {@link Sphere#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);

        // Use cleaner values for intersection test
        final var result1 = sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, 1, 0)));
        final var exp = List.of(new Point(1, -1, 0), new Point(1, 1, 0));

        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        assertNotNull(result1, "Can't be empty list");
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        var result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0)));
        assertNotNull(result, "Ray from inside sphere");
        assertEquals(1, result.size(), "Wrong number of points");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Ray starts after sphere");

        // =============== Boundary Values Tests ==================
        // **** Group 1: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0)));
        assertNotNull(result, "Ray from sphere surface inside");
        assertEquals(1, result.size(), "Wrong number of points");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Ray from sphere surface outside");

        // **** Group 2: Ray's line goes through the center
        // TC21: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, 1, 0)));
        assertNotNull(result, "Line through center, ray crosses sphere");
        assertEquals(2, result.size(), "Wrong number of points");

        // TC22: Ray starts at sphere and goes inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, 1, 0)));
        assertNotNull(result, "Line through center, ray from sphere inside");
        assertEquals(1, result.size(), "Wrong number of points");

        // TC23: Ray starts inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(0, 1, 0)));
        assertNotNull(result, "Line through center, ray from inside");
        assertEquals(1, result.size(), "Wrong number of points");

        // TC24: Ray starts at the center (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)));
        assertNotNull(result, "Line through center, ray from center");
        assertEquals(1, result.size(), "Wrong number of points");

        // TC25: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "Line through center, ray from sphere outside");

        // TC26: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 1, 0))),
                "Line through center, ray outside sphere");

        // **** Group 3: Ray's line is tangent to the sphere (all tests 0 points)
        // TC31: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray before sphere");

        // TC32: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray at sphere");

        // TC33: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray after sphere");

        // **** Group 4: Special cases
        // TC41: Ray's line is outside sphere, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 0, 1))),
                "Ray orthogonal to ray head -> sphere center line");

        // TC42: Ray starts inside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray starts inside, orthogonal to ray head -> sphere center line");
        assertEquals(1, result.size(), "Wrong number of points");
    }
}