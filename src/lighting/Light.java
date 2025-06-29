package lighting;

import primitives.Color;

/**
 * Abstract class Light represents a light source in a 3D scene.
 * Serves as a base class for all types of light sources.
 * Package-private access (no access modifier)
 *
 * @author Maor Atari
 */
abstract class Light {

    /**
     * The intensity of the light source
     * Protected access allows subclasses to access it
     * Final ensures it cannot be changed after construction
     */
    protected final Color intensity;

    /**
     * Constructor to initialize Light with given intensity
     * Protected access - only subclasses can create instances
     *
     * @param intensity the intensity of the light as Color
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for the light intensity
     * Public access for external use
     *
     * @return the intensity of the light as Color
     */
    public Color getIntensity() {
        return intensity;
    }
}