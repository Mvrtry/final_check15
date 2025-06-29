package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface LightSource represents a light source that can illuminate objects in a scene.
 * Defines methods for getting light intensity and direction at a given point.
 *
 * @author Maor Atari
 */
public interface LightSource {

    /**
     * Gets the intensity of light at a specific point
     *
     * @param p the point to calculate light intensity for
     * @return the color intensity of the light at point p
     */
    Color getIntensity(Point p);

    /**
     * Gets the direction vector from the light source to a given point
     *
     * @param p the point to calculate direction to
     * @return the normalized direction vector from light source to point p
     */
    Vector getL(Point p);
}