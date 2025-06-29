package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class PointLight represents a point light source (like a light bulb).
 * Extends Light and implements LightSource interface.
 * Light intensity decreases with distance according to attenuation factors.
 *
 * @author Maor Atari
 */
public class PointLight extends Light implements LightSource {

    /**
     * The position of the point light source
     * Final - cannot be changed after construction
     */
    private final Point position;

    /**
     * Constant attenuation factor
     * Default value: 1 (no constant attenuation by default)
     */
    private double kC = 1;

    /**
     * Linear attenuation factor
     * Default value: 0 (no linear attenuation by default)
     */
    private double kL = 0;

    /**
     * Quadratic attenuation factor
     * Default value: 0 (no quadratic attenuation by default)
     */
    private double kQ = 0;

    /**
     * Constructor to initialize PointLight with intensity and position
     *
     * @param intensity the color intensity of the light
     * @param position  the position of the light source
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Setter for constant attenuation factor (Builder pattern style)
     *
     * @param kC the constant attenuation factor
     * @return this PointLight object for method chaining
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Setter for linear attenuation factor (Builder pattern style)
     *
     * @param kL the linear attenuation factor
     * @return this PointLight object for method chaining
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter for quadratic attenuation factor (Builder pattern style)
     *
     * @param kQ the quadratic attenuation factor
     * @return this PointLight object for method chaining
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        // Calculate distance from light source to point
        double distance = position.distance(p);

        // Apply attenuation formula: I₀ / (kC + kL*d + kQ*d²)
        double attenuation = kC + kL * distance + kQ * distance * distance;

        return intensity.scale(1.0 / attenuation);
    }

    @Override
    public Vector getL(Point p) {
        // Direction from light source to point
        return p.subtract(position).normalize();
    }
}