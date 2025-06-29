package unittests.geometries;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Geometries} class
 * Tests the findIntersections method with various scenarios including
 * boundary value analysis (BVA) and equivalence partitioning (EP)
 *
 * @author Maor Atari
 */
public class GeometriesTests {

    /**
     * Default constructor
     */
    public GeometriesTests() {
    }

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}
     * Tests various intersection scenarios:
     * - Empty collection (BVA)
     * - No intersections (BVA)
     * - Single intersection (BVA)
     * - Multiple intersections (EP)
     */
    @Test
    void testFindIntersections() {
        Geometries geometries = new Geometries();

        // =============== Boundary Values Tests ==================

        // TC01: Empty geometries collection (BVA)
        assertNull(geometries.findIntersections(new Ray(new Point(1, 2, 3), new Vector(0, 0, 1))),
                "Empty geometries collection must return null");

        // Add geometries with nice simple coordinates
        Sphere sphere = new Sphere(new Point(2, 0, 0), 1);
        Triangle triangle = new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(-1, 0, 1));
        Plane plane = new Plane(new Point(1, 1, 2), new Vector(0, 0, 1));

        geometries.add(sphere, triangle, plane);

        // TC02: Ray that misses all geometries (BVA)
        assertNull(geometries.findIntersections(new Ray(new Point(5, 5, 5), new Vector(1, 0, 0))),
                "Ray misses all geometries");

        // TC03: Ray that intersects one geometry only - the plane (BVA)
        List<Point> result = geometries.findIntersections(new Ray(new Point(3, 3, 1), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect one geometry");
        assertEquals(1, result.size(), "Wrong number of intersections");

        // ============ Equivalence Partitions Tests ==============

        // TC04: Ray that intersects some geometries (EP) - sphere and plane
        result = geometries.findIntersections(new Ray(new Point(2, 0, -2), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect some geometries");
        assertEquals(3, result.size(), "Wrong number of intersections"); // 2 from sphere + 1 from plane

        // TC05: Ray that intersects multiple geometries (EP) - triangle and plane
        result = geometries.findIntersections(new Ray(new Point(0, 0.3, 0), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect multiple geometries");
        assertEquals(2, result.size(), "Should intersect triangle and plane"); // 1 from triangle + 1 from plane

        // TC06: All geometries intersected (BVA)
        // Create a simpler setup where ray intersects all three geometries
        Geometries allIntersected = new Geometries();
        allIntersected.add(
                new Sphere(new Point(1, 1, 0), 1),           // Sphere at origin with radius 1
                new Triangle(new Point(-1, -1, 1), new Point(3, -1, 1), new Point(1, 3, 1)), // Large triangle at z=1
                new Plane(new Point(1, 1, 2), new Vector(0, 0, 1))  // Plane at z=2
        );

        result = allIntersected.findIntersections(new Ray(new Point(1, 1, -2), new Vector(0, 0, 1)));
        assertNotNull(result, "Ray should intersect all geometries");
        assertEquals(4, result.size(), "Should intersect all geometries"); // 2 from sphere + 1 from triangle + 1 from plane
    }
}