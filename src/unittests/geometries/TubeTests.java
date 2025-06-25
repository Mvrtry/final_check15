package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for geometries.Tube class
 *
 * @author Maor Atari
 */
class TubeTests {
    /**
     * Default constructor for TubeTests
     */
    public TubeTests() {
    }

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test normal vector calculation for tube
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Tube tube = new Tube(axisRay, 1);
        Vector normal = tube.getNormal(new Point(1, 0, 1));

        // Ensure the result is a unit vector
        assertEquals(1, normal.length(), DELTA, "Tube's normal is not a unit vector");

        // Ensure the normal is perpendicular to the axis
        assertEquals(0, normal.dotProduct(axisRay.getDir()), DELTA,
                "Tube's normal is not perpendicular to axis");

        // The normal should point from axis to the point
        Vector expected = new Vector(1, 0, 0);
        assertEquals(expected, normal, "Tube's normal direction is incorrect");

        // =============== Boundary Values Tests ==================

        // TC11: Test when point projection coincides with ray start point
        Vector normal2 = tube.getNormal(new Point(1, 0, 0));
        assertEquals(1, normal2.length(), DELTA, "Tube's normal is not a unit vector at boundary");
        assertEquals(0, normal2.dotProduct(axisRay.getDir()), DELTA,
                "Tube's normal is not perpendicular to axis at boundary");
    }
}