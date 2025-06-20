package primitives;

/**
 * Class Ray is the basic class representing a ray (half-infinite line) of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Ray {
    /**
     * The starting point of the ray
     */
    private final Point p0;
    /**
     * The direction vector of the ray (always normalized)
     */
    private final Vector dir;

    /**
     * Constructor to initialize Ray based on starting point and direction vector
     *
     * @param p0  the starting point
     * @param dir the direction vector (will be normalized)
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.p0.equals(other.p0)
                && this.dir.equals(other.dir);
    }

    @Override
    public String toString() {
        return "Ray{" + "p0=" + p0 + ", dir=" + dir + '}';
    }
}