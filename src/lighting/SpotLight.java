package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class SpotLight represents a spotlight source (like a flashlight).
 * Extends PointLight and adds directional constraint.
 * Light intensity decreases with distance and also depends on direction.
 *
 * @author Maor Atari
 */
public class SpotLight extends PointLight {

    /**
     * The direction of the spotlight beam
     * Final - cannot be changed after construction
     */
    private final Vector direction;

    /**
     * Constructor to initialize SpotLight with intensity, position and direction
     *
     * @param intensity the color intensity of the light
     * @param position  the position of the light source
     * @param direction the direction of the spotlight beam
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Setter for constant attenuation factor (Builder pattern style)
     * Overrides parent method to return SpotLight for proper chaining
     *
     * @param kC the constant attenuation factor
     * @return this SpotLight object for method chaining
     */
    @Override
    public SpotLight setKc(double kC) {
        super.setKc(kC);
        return this;
    }

    /**
     * Setter for linear attenuation factor (Builder pattern style)
     * Overrides parent method to return SpotLight for proper chaining
     *
     * @param kL the linear attenuation factor
     * @return this SpotLight object for method chaining
     */
    @Override
    public SpotLight setKl(double kL) {
        super.setKl(kL);
        return this;
    }

    /**
     * Setter for quadratic attenuation factor (Builder pattern style)
     * Overrides parent method to return SpotLight for proper chaining
     *
     * @param kQ the quadratic attenuation factor
     * @return this SpotLight object for method chaining
     */
    @Override
    public SpotLight setKq(double kQ) {
        super.setKq(kQ);
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        // Get base intensity from parent (with distance attenuation)
        Color baseIntensity = super.getIntensity(p);

        // Calculate direction from light to point
        Vector l = getL(p);

        // Calculate beam factor: cosine of angle between beam direction and light-to-point direction
        double beamFactor = Math.max(0, direction.dotProduct(l));

        // Apply beam factor to intensity
        return baseIntensity.scale(beamFactor);
    }
    /**
     * Narrow beam factor for concentrated spotlight beam
     * Default value: 1 (no narrowing by default)
     */
    private double narrowBeam = 1;

    /**
     * Setter for narrow beam factor (Builder pattern style)
     *
     * @param narrowBeam the narrow beam factor
     * @return this SpotLight object for method chaining
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
}