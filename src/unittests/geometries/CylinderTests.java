package unittests.geometries;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for geometries.Cylinder class
 *
 * @author Maor Atari
 */
class CylinderTests {
    /**
     * Default constructor for CylinderTests
     */
    public CylinderTests() {
    }

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder cylinder = new Cylinder(axisRay, 1, 2);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Point on curved surface
        Vector normal1 = cylinder.getNormal(new Point(1, 0, 1));
        assertEquals(1, normal1.length(), DELTA, "Cylinder's normal is not a unit vector");
        assertEquals(0, normal1.dotProduct(axisRay.getDir()), DELTA,
                "Cylinder's normal on curved surface is not perpendicular to axis");

        // TC02: Point on bottom base (0.5 units from center)
        Vector normal2 = cylinder.getNormal(new Point(0.5, 0, 0));
        Vector expectedBottom = new Vector(0, 0, -1);
        assertEquals(expectedBottom, normal2, "Cylinder's normal on bottom base is incorrect");

        // TC03: Point on top base (0.5 units from center)
        Vector normal3 = cylinder.getNormal(new Point(0.5, 0, 2));
        Vector expectedTop = new Vector(0, 0, 1);
        assertEquals(expectedTop, normal3, "Cylinder's normal on top base is incorrect");

        // =============== Boundary Values Tests ==================

        // TC11: Point very close to center of bottom base (0.1 units from center)
        Vector normal4 = cylinder.getNormal(new Point(0.1, 0, 0));
        assertEquals(expectedBottom, normal4, "Cylinder's normal near center of bottom base is incorrect");

        // TC12: Point very close to center of top base (0.1 units from center)
        Vector normal5 = cylinder.getNormal(new Point(0.1, 0, 2));
        assertEquals(expectedTop, normal5, "Cylinder's normal near center of top base is incorrect");

        // TC13: Point on edge between bottom base and curved surface
        Vector normal6 = cylinder.getNormal(new Point(1, 0, 0));
        assertEquals(expectedBottom, normal6, "Cylinder's normal on bottom edge is incorrect");

        // TC14: Point on edge between top base and curved surface
        Vector normal7 = cylinder.getNormal(new Point(1, 0, 2));
        assertEquals(expectedTop, normal7, "Cylinder's normal on top edge is incorrect");
    }
}