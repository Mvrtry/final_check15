package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Scene represents a 3D scene containing geometries, lighting, and light sources.
 * This is a Plain Data Structure (PDS) - a passive data container.
 * All fields are public for direct access, no getters needed.
 *
 * @author Maor Atari
 */
public class Scene {

    /**
     * The name of the scene
     */
    public final String name;

    /**
     * Background color of the scene
     * Default initialized to black
     */
    public Color background = Color.BLACK;

    /**
     * Ambient lighting of the scene
     * Default initialized to no ambient light
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * 3D model containing all geometries in the scene
     * Default initialized to empty geometries collection
     */
    public Geometries geometries = new Geometries();

    /**
     * List of light sources in the scene
     * Default initialized to empty linked list
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor to initialize Scene with a name
     * Only the name is set, other fields use default values
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Setter for background color
     * Returns this scene object for method chaining (Builder pattern style)
     *
     * @param background the background color to set
     * @return this Scene object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter for ambient light
     * Returns this scene object for method chaining (Builder pattern style)
     *
     * @param ambientLight the ambient light to set
     * @return this Scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter for geometries collection
     * Returns this scene object for method chaining (Builder pattern style)
     *
     * @param geometries the geometries collection to set
     * @return this Scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Setter for lights collection
     * Returns this scene object for method chaining (Builder pattern style)
     *
     * @param lights the lights collection to set
     * @return this Scene object
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}