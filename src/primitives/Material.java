package primitives;

/**
 * Class Material represents material properties of geometric objects.
 * This is a Passive Data Structure (PDS) - a simple data container.
 * Contains material coefficients for lighting calculations.
 *
 * @author Maor Atari
 */
public class Material {

    /**
     * Ambient light attenuation coefficient for the material
     * Public access for direct use as PDS
     * Initialized to Double3.ONE (full reflection by default)
     */
    public Double3 kA = Double3.ONE;

    /**
     * Diffuse reflection coefficient for the material
     * Public access for direct use as PDS
     * Initialized to Double3.ZERO (no diffuse reflection by default)
     */
    public Double3 kD = Double3.ZERO;

    /**
     * Specular reflection coefficient for the material
     * Public access for direct use as PDS
     * Initialized to Double3.ZERO (no specular reflection by default)
     */
    public Double3 kS = Double3.ZERO;

    /**
     * Shininess factor for specular reflection
     * Public access for direct use as PDS
     * Initialized to 0 (no shininess by default)
     */
    public int nShininess = 0;

    /**
     * Default constructor
     * No explicit constructor needed - compiler provides implicit default constructor
     * Can be used to avoid JavaDoc warnings if desired
     */
    public Material() {
        // Empty default constructor
    }

    /**
     * Setter for ambient coefficient with Double3 parameter
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param kA the ambient attenuation coefficient as Double3
     * @return this Material object
     */
    public Material setKA(Double3 kA) {
        this.kA = kA;
        return this;
    }

    /**
     * Setter for ambient coefficient with double parameter
     * Creates a Double3 with the same value for all three components
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param kA the ambient attenuation coefficient as double (applied to all RGB components)
     * @return this Material object
     */
    public Material setKA(double kA) {
        this.kA = new Double3(kA);
        return this;
    }

    /**
     * Setter for diffuse coefficient with Double3 parameter
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param kD the diffuse reflection coefficient as Double3
     * @return this Material object
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter for diffuse coefficient with double parameter
     * Creates a Double3 with the same value for all three components
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param kD the diffuse reflection coefficient as double (applied to all RGB components)
     * @return this Material object
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter for specular coefficient with Double3 parameter
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param kS the specular reflection coefficient as Double3
     * @return this Material object
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter for specular coefficient with double parameter
     * Creates a Double3 with the same value for all three components
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param kS the specular reflection coefficient as double (applied to all RGB components)
     * @return this Material object
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Setter for shininess factor
     * Returns this Material object for method chaining (Builder pattern style)
     *
     * @param nShininess the shininess factor for specular reflection
     * @return this Material object
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}