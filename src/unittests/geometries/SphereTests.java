package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for geometries.Sphere class
 * @author Maor Atari
 */
class SphereTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /** Test method for {@link geometries.Sphere#getNormal(primitives.Point)}. */
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
}