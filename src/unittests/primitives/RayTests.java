package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for primitives.Ray class
 *
 * @author Maor Atari
 */
public class RayTests {
    /**
     * Default constructor for RayTests
     */
    public RayTests() {
    }

    /**
     * Test method for {@link Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Positive distance
        Point p1 = ray.getPoint(2);
        assertEquals(new Point(1, 4, 3), p1, "Wrong point for positive distance");

        // TC02: Negative distance
        Point p2 = ray.getPoint(-1);
        assertEquals(new Point(1, 1, 3), p2, "Wrong point for negative distance");

        // =============== Boundary Values Tests ==================
        // TC11: Zero distance
        Point p3 = ray.getPoint(0);
        assertEquals(new Point(1, 2, 3), p3, "Wrong point for zero distance");

        // Additional test cases for better coverage
        // TC12: Distance of 1
        Point p4 = ray.getPoint(1);
        assertEquals(new Point(1, 3, 3), p4, "Wrong point for distance of 1");

        // TC13: Small positive distance
        Point p5 = ray.getPoint(0.5);
        assertEquals(new Point(1, 2.5, 3), p5, "Wrong point for small positive distance");

        // TC14: Large positive distance
        Point p6 = ray.getPoint(10);
        assertEquals(new Point(1, 12, 3), p6, "Wrong point for large positive distance");
    }

    /**
     * Test method for {@link Ray#findClosestPoint(List)}.
     */
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));

        // =============== Boundary Values Tests ==================

        // TC11: Empty list (null) - should return null
        assertNull(ray.findClosestPoint(null), "Empty list should return null");

        // TC12: First point is the closest to ray's starting point
        List<Point> pointsFirstClosest = List.of(
                new Point(1, 0, 0),    // Distance: 1 (closest)
                new Point(3, 0, 0),    // Distance: 3
                new Point(5, 0, 0)     // Distance: 5
        );
        assertEquals(new Point(1, 0, 0), ray.findClosestPoint(pointsFirstClosest),
                "First point should be the closest");

        // TC13: Last point is the closest to ray's starting point
        List<Point> pointsLastClosest = List.of(
                new Point(5, 0, 0),    // Distance: 5
                new Point(3, 0, 0),    // Distance: 3
                new Point(1, 0, 0)     // Distance: 1 (closest)
        );
        assertEquals(new Point(1, 0, 0), ray.findClosestPoint(pointsLastClosest),
                "Last point should be the closest");

        // ============ Equivalence Partitions Tests ==============

        // TC01: Middle point is the closest to ray's starting point
        List<Point> pointsMiddleClosest = List.of(
                new Point(5, 0, 0),    // Distance: 5
                new Point(1, 0, 0),    // Distance: 1 (closest)
                new Point(3, 0, 0)     // Distance: 3
        );
        assertEquals(new Point(1, 0, 0), ray.findClosestPoint(pointsMiddleClosest),
                "Middle point should be the closest");
    }
}