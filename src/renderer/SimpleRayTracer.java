package renderer;

import geometries.Intersectable.Intersection;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Simple implementation of ray tracer
 * Provides ray tracing functionality using Phong lighting model
 *
 * @author Maor Atari
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructor to initialize simple ray tracer with a scene
     * Calls the parent constructor to set up the scene
     *
     * @param scene the 3D scene to be rendered
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray through the scene and calculates its color intensity
     * Implements ray tracing with Phong lighting model
     *
     * @param ray the ray to trace through the scene
     * @return the color intensity of the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        // Find intersections between ray and scene geometries using new intersection system
        List<Intersection> intersections = scene.geometries.calculateIntersections(ray);

        // If no intersections found, return background color
        if (intersections == null) {
            return scene.background;
        }

        // Find the closest intersection point to ray origin
        Intersection closestIntersection = ray.findClosestIntersection(intersections);

        // Calculate and return the color at the intersection point
        return calcColor(closestIntersection, ray);
    }

    /**
     * Calculates the color at a given intersection in the scene
     * Updated to use Phong lighting model with ray parameter
     *
     * @param intersection the intersection data containing geometry and point information
     * @param ray the ray that hit the intersection
     * @return the color at the given intersection
     */
    private Color calcColor(Intersection intersection, Ray ray) {
        // Initialize intersection data for lighting calculations
        if (!preprocessIntersection(intersection, ray.getDir())) {
            // If preprocessing failed (e.g., ray perpendicular to surface), return black
            return Color.BLACK;
        }

        // Calculate local lighting effects using Phong model
        return calcColorLocalEffects(intersection);
    }

    /**
     * Preprocesses intersection data for lighting calculations
     * Initializes ray direction, normal vector, and their dot product
     *
     * @param intersection the intersection to preprocess
     * @param rayDirection the direction of the intersecting ray
     * @return false if dot product is zero (ray perpendicular to surface), true otherwise
     */
    private boolean preprocessIntersection(Intersection intersection, Vector rayDirection) {
        // Store ray direction
        intersection.rayDirection = rayDirection;

        // Calculate normal at intersection point
        intersection.normal = intersection.geometry.getNormal(intersection.point);

        // Calculate dot product of normal and ray direction
        intersection.nDotRayDir = intersection.normal.dotProduct(rayDirection);

        // Return false if dot product is zero (perpendicular ray)
        return !isZero(intersection.nDotRayDir);
    }

    /**
     * Sets light source data for intersection
     * Initializes light source, light direction, and their dot product
     *
     * @param intersection the intersection to set light data for
     * @param lightSource the light source to process
     * @return false if either dot product is zero, true otherwise
     */
    private boolean setLightSource(Intersection intersection, LightSource lightSource) {
        // Store light source
        intersection.lightSource = lightSource;

        // Calculate light direction (from light to point)
        intersection.lightDirection = lightSource.getL(intersection.point);

        // Calculate dot product of normal and light direction
        intersection.nDotLightDir = intersection.normal.dotProduct(intersection.lightDirection);

        // Return false if either dot product is zero
        return !isZero(intersection.nDotRayDir) && !isZero(intersection.nDotLightDir);
    }

    /**
     * Calculates local lighting effects using Phong model
     * Combines emission color with ambient, diffuse, and specular components
     *
     * @param intersection the intersection to calculate lighting for
     * @return the final color including all local lighting effects
     */
    private Color calcColorLocalEffects(Intersection intersection) {
        // Start with emission color of the geometry
        Color color = intersection.geometry.getEmission();

        // Add ambient light component
        color = color.add(scene.ambientLight.getIntensity().scale(intersection.material.kA));

        // Process each light source in the scene
        for (LightSource lightSource : scene.lights) {
            // Set light source data for this intersection
            if (!setLightSource(intersection, lightSource)) {
                continue; // Skip this light if setup failed
            }

            // Get light intensity at intersection point
            Color lightIntensity = lightSource.getIntensity(intersection.point);

            // Calculate diffuse and specular components
            Double3 diffuse = calcDiffusive(intersection);
            Double3 specular = calcSpecular(intersection);

            // Add diffuse and specular contributions
            color = color.add(lightIntensity.scale(diffuse.add(specular)));
        }

        return color;
    }

    /**
     * Calculates the diffuse reflection component using Lambert's law
     *
     * @param intersection the intersection data
     * @return the diffuse reflection coefficient
     */
    private Double3 calcDiffusive(Intersection intersection) {
        // Diffuse reflection: kD * max(0, n·l)
        double nDotL = Math.abs(intersection.nDotLightDir);
        return intersection.material.kD.scale(nDotL);
    }

    /**
     * Calculates the specular reflection component using Phong model
     *
     * @param intersection the intersection data
     * @return the specular reflection coefficient
     */
    private Double3 calcSpecular(Intersection intersection) {
        // Calculate reflection vector: r = l - 2(n·l)n
        Vector l = intersection.lightDirection.scale(-1); // Light direction (from point to light)
        Vector n = intersection.normal;
        double nDotL = intersection.nDotLightDir;

        Vector r = l.subtract(n.scale(2 * nDotL));

        // Calculate view direction (from point to camera): v = -rayDirection
        Vector v = intersection.rayDirection.scale(-1);

        // Calculate dot product of reflection and view vectors
        double rDotV = r.dotProduct(v);

        // Specular reflection: kS * max(0, r·v)^nShininess
        if (rDotV <= 0) {
            return Double3.ZERO; // No specular reflection
        }

        double specularFactor = Math.pow(rDotV, intersection.material.nShininess);
        return intersection.material.kS.scale(specularFactor);
    }
}