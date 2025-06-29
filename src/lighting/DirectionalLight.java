package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class DirectionalLight represents a directional light source (like sunlight).
 * Extends Light and implements LightSource interface.
 * Provides uniform illumination from a specific direction.
 *
 * @author Maor Atari
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * The direction of the light (from light source towards objects)
     * Final - cannot be changed after construction
     */
    private final Vector direction;

    /**
     * Constructor to initialize DirectionalLight with intensity and direction
     *
     * @param intensity the color intensity of the light
     * @param direction the direction vector of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        // Directional light has constant intensity regardless of point location
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        // Direction from light to point is always the same (opposite to light direction)
        return direction.scale(-1);
    }
}