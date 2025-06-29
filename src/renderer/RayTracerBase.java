package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract base class for ray tracers
 * Provides common functionality and interface for different ray tracing implementations
 *
 * @author Maor Atari
 */
public abstract class RayTracerBase {

    /**
     * The 3D scene containing geometries and lighting information
     * Protected access allows subclasses to use it, final ensures immutability
     */
    protected final Scene scene;

    /**
     * Constructor to initialize ray tracer with a scene
     *
     * @param scene the 3D scene to be rendered
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Abstract method to trace a ray through the scene and calculate its color intensity
     * Implementation depends on the specific ray tracing algorithm used
     *
     * @param ray the ray to trace through the scene
     * @return the color intensity of the ray as determined by the ray tracing algorithm
     */
    public abstract Color traceRay(Ray ray);
}