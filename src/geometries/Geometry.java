package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Abstract class Geometry is the basic class representing any geometry in Cartesian
 * 3-Dimensional coordinate system. Extends Intersectable abstract class.
 *
 * @author Maor Atari
 */
public abstract class Geometry extends Intersectable {

    /**
     * The emission color of the geometry (for luminous/emissive surfaces)
     * Protected access allows subclasses to access it
     * Initialized to black (no emission by default)
     */
    protected Color emission = Color.BLACK;

    /**
     * The material properties of the geometry
     * Private access with getter/setter for encapsulation
     * Initialized with default material
     */
    private Material material = new Material();

    /**
     * Default constructor for Geometry.
     */
    public Geometry() {
    }

    /**
     * Getter for the emission color
     *
     * @return the emission color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Setter for the emission color (Builder pattern style)
     *
     * @param emission the emission color to set
     * @return this geometry object for method chaining
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Getter for the material properties
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Setter for the material properties (Builder pattern style)
     *
     * @param material the material to set
     * @return this geometry object for method chaining
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns the normal vector to the geometry at a given point
     *
     * @param point the point on the geometry's surface
     * @return normal vector at the point
     */
    public abstract Vector getNormal(Point point);
}