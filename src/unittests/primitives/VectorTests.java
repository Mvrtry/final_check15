package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Maor Atari
 */
class VectorTests {
    /**
     * Default constructor for VectorTests
     */
    public VectorTests() {
    }

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link Vector#Vector(double, double, double)}.
     */
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================

        // TC11: Creating zero vector should throw exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
                "Zero vector does not throw an exception");

        // TC12: Creating zero vector with Double3 should throw exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO),
                "Zero vector with Double3 does not throw an exception");
    }

    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple addition test
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 3, 4);
        Vector expected = new Vector(3, 5, 7);
        assertEquals(expected, v1.add(v2), "Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC11: Adding opposite vectors should throw exception
        Vector v3 = new Vector(-1, -2, -3);
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3),
                "Vector + opposite vector does not throw an exception");
    }

    /**
     * Test method for {@link Vector#subtract(Vector)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple subtraction test
        Vector v1 = new Vector(3, 5, 7);
        Vector v2 = new Vector(1, 2, 3);
        Vector expected = new Vector(2, 3, 4);
        assertEquals(expected, v1.subtract(v2), "Vector - Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC11: Subtracting vector from itself should throw exception
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1),
                "Vector - itself does not throw an exception");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Scaling with positive number
        Vector v1 = new Vector(1, 2, 3);
        Vector expected = new Vector(2, 4, 6);
        assertEquals(expected, v1.scale(2), "Vector scaling with positive number does not work correctly");

        // TC02: Scaling with negative number
        Vector expected2 = new Vector(-1, -2, -3);
        assertEquals(expected2, v1.scale(-1), "Vector scaling with negative number does not work correctly");

        // =============== Boundary Values Tests ==================

        // TC11: Scaling with zero should throw exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),
                "Vector scaling with zero does not throw an exception");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Dot product of regular vectors
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 3, 4);
        assertEquals(20, v1.dotProduct(v2), DELTA, "Dot product calculation is wrong");

        // TC02: Dot product of orthogonal vectors
        Vector v3 = new Vector(0, 3, -2);
        assertEquals(0, v1.dotProduct(v3), DELTA, "Dot product of orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), DELTA, "crossProduct() wrong result length");
        assertEquals(0, vr.dotProduct(v1), DELTA, "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v2), DELTA, "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================

        // TC11: test zero vector from cross-product of parallel vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple length squared test
        Vector v1 = new Vector(1, 2, 2);
        assertEquals(9, v1.lengthSquared(), DELTA, "lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple length test
        Vector v1 = new Vector(1, 2, 2);
        assertEquals(3, v1.length(), DELTA, "length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test vector normalization vs vector length and cross-product
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(1, u.length(), DELTA, "the normalized vector is not a unit vector");

        // TC02: Test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u),
                "the normalized vector is not parallel to the original one");

        // TC03: Test that normalized vector has same direction
        assertTrue(v.dotProduct(u) > 0, "the normalized vector is opposite to the original one");
    }

}
