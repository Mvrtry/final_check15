package geometries;

import lighting.LightSource;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Abstract class representing geometries that can be intersected by a ray
 * Uses the NVI (Non-Virtual Interface) design pattern
 *
 * @author Maor Atari
 */
public abstract class Intersectable {

    /**
     * Static inner class representing an intersection point with additional data
     * This is a Passive Data Structure (PDS) containing intersection information
     * Extended to include caching for lighting calculations
     */
    public static class Intersection {
        /**
         * The geometry that was intersected
         */
        public final Geometry geometry;

        /**
         * The intersection point
         */
        public final Point point;

        /**
         * The material of the intersected geometry
         * Final field initialized in constructor (if geometry is not null)
         */
        public final Material material;

        // Cache fields for lighting calculations (not final, not initialized in class)

        /**
         * Direction vector of the ray that caused this intersection
         */
        public Vector rayDirection;

        /**
         * Normal vector to the geometry surface at intersection point
         */
        public Vector normal;

        /**
         * Dot product of ray direction and normal vector
         */
        public double nDotRayDir;

        /**
         * The light source being processed for this intersection
         */
        public LightSource lightSource;

        /**
         * Direction vector from light source to intersection point
         */
        public Vector lightDirection;

        /**
         * Dot product of light direction and normal vector
         */
        public double nDotLightDir;

        /**
         * Constructor for Intersection
         *
         * @param geometry the intersected geometry
         * @param point    the intersection point
         */
        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
            // Initialize material only if geometry is not null
            this.material = geometry != null ? geometry.getMaterial() : null;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Intersection that = (Intersection) obj;

            // Compare geometries by reference (same object at same address)
            if (geometry != that.geometry) return false;

            // Compare points using equals method (for coordinate comparison)
            return point != null ? point.equals(that.point) : that.point == null;
        }

        @Override
        public int hashCode() {
            int result = geometry != null ? System.identityHashCode(geometry) : 0;
            result = 31 * result + (point != null ? point.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Intersection{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * Helper method for calculating intersections (to be implemented by subclasses)
     * Protected access for NVI pattern - cannot be private due to Java limitations with abstract methods
     *
     * @param ray the ray to check intersection with
     * @return list of intersection objects, or null if there are no intersections
     */
    protected abstract List<Intersection> calculateIntersectionsHelper(Ray ray);

    /**
     * Calculates intersections between a ray and the geometry using NVI pattern
     * Final method that calls the helper method
     *
     * @param ray the ray to check intersection with
     * @return list of intersection objects, or null if there are no intersections
     */
    public final List<Intersection> calculateIntersections(Ray ray) {
        return calculateIntersectionsHelper(ray);
    }

    /**
     * Finds intersection points between a ray and the geometry
     * This method maintains backward compatibility while using the new intersection system
     *
     * @param ray the ray to check intersection with
     * @return list of intersection points, or null if there are no intersections
     */
    public final List<Point> findIntersections(Ray ray) {
        var list = calculateIntersections(ray);
        return list == null ? null : list.stream().map(intersection -> intersection.point).toList();
    }
}