package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Geometries represents a collection of intersectable geometries
 * Extends the Intersectable abstract class using the Composite design pattern
 * Allows treating individual geometries and collections of geometries uniformly
 *
 * @author Maor Atari
 */
public class Geometries extends Intersectable {

    /**
     * Collection of intersectable geometries
     * Private and final to ensure encapsulation and immutability of the reference
     */
    private final List<Intersectable> geometries;

    /**
     * Default constructor
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * Constructor with initial geometries
     * Creates a collection and adds the provided geometries to it
     *
     * @param geometries initial geometries to add to the collection (varargs)
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        add(geometries);
    }

    /**
     * Add geometries to the collection
     * Filters out null values to maintain collection integrity
     *
     * @param geometries geometries to add to the collection (varargs)
     */
    public void add(Intersectable... geometries) {
        if (geometries != null) {
            for (Intersectable geometry : geometries) {
                if (geometry != null) {
                    this.geometries.add(geometry);
                }
            }
        }
    }

    /**
     * Implementation of calculateIntersectionsHelper for the Composite pattern
     * Delegates intersection calculation to each geometry and collects results
     *
     * @param ray the ray to intersect with the geometries
     * @return list of intersection objects, or null if no intersections found
     */
    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {
        if (ray == null) return null;

        List<Intersection> result = null;

        for (Intersectable geometry : geometries) {
            List<Intersection> intersections = geometry.calculateIntersections(ray);

            if (intersections != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(intersections);
            }
        }

        return result;
    }
}