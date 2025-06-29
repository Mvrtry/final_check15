package lighting;

import primitives.Color;

/**
 * Class AmbientLight represents ambient lighting in a 3D scene.
 * Ambient light provides uniform illumination from all directions.
 * Inherits from Light abstract class.
 *
 * @author Maor Atari
 */
public class AmbientLight extends Light {

    /**
     * Static constant representing no ambient light (black/zero intensity)
     * Public access for easy reference
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK);

    /**
     * Constructor to initialize AmbientLight with given intensity
     * Calls parent constructor to set the intensity
     *
     * @param intensity the intensity of the ambient light (IA) as Color
     */
    public AmbientLight(Color intensity) {
        super(intensity);
    }
}