package unittests.renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for camera ray construction with intersections
 * Tests camera rays intersections with sphere, plane and triangle
 *
 * @author Maor Atari
 */
public class CameraIntersectionsIntegrationTests {

    /**
     * default contractor
     */
    public CameraIntersectionsIntegrationTests() {}

    /**
     * Helper method to count intersections of all rays from camera with a geometry
     *
     * @param camera the camera
     * @param geometry the geometry to test intersections with
     * @return total number of intersection points
     */
    private int countIntersections(Camera camera, Intersectable geometry) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRay(3, 3, j, i);
                List<Point> intersections = geometry.findIntersections(ray);
                if (intersections != null) {
                    count += intersections.size();
                }
            }
        }
        return count;
    }

    /**
     * Integration tests for camera ray construction with sphere intersections
     */
    @Test
    void testCameraRaySphereIntegration() {
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1)
                .setVpSize(3, 3);

        Camera camera = cameraBuilder.build();

        // TC01: Small sphere in front of camera - 2 intersection points
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2, countIntersections(camera, sphere),
                "Wrong number of intersections - small sphere");

        // TC02: Large sphere encompasses view plane - 18 intersection points
        // Move camera slightly forward to ensure all rays hit the sphere twice
        Camera camera2 = cameraBuilder.setLocation(new Point(0, 0, 0.5)).build();
        sphere = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, countIntersections(camera2, sphere),
                "Wrong number of intersections - large sphere");

        // TC03: Medium sphere - 10 intersection points
        sphere = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, countIntersections(camera2, sphere),
                "Wrong number of intersections - medium sphere");

        // TC04: Camera inside sphere - 9 intersection points
        // Reset camera to original position
        sphere = new Sphere(new Point(0, 0, -1), 4);
        assertEquals(9, countIntersections(camera2, sphere),
                "Wrong number of intersections - camera inside sphere");

        // TC05: Sphere behind camera - 0 intersection points
        sphere = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, countIntersections(camera2, sphere),
                "Wrong number of intersections - sphere behind camera");
    }

    /**
     * Integration tests for camera ray construction with plane intersections
     */
    @Test
    void testCameraRayPlaneIntegration() {
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1)
                .setVpSize(3, 3);

        Camera camera = cameraBuilder.build();

        // TC01: Plane parallel to view plane - 9 intersection points
        Plane plane = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1));
        assertEquals(9, countIntersections(camera, plane),
                "Wrong number of intersections - parallel plane");

        // TC02: Plane slightly tilted - 9 intersection points
        plane = new Plane(new Point(0, 0, -5), new Vector(0, 0.5, 1));
        assertEquals(9, countIntersections(camera, plane),
                "Wrong number of intersections - slightly tilted plane");

        // TC03: Plane strongly tilted - 6 intersection points
        plane = new Plane(new Point(0, 0, -5), new Vector(0, 1, 1));
        assertEquals(6, countIntersections(camera, plane),
                "Wrong number of intersections - strongly tilted plane");
    }

    /**
     * Integration tests for camera ray construction with triangle intersections
     */
    @Test
    void testCameraRayTriangleIntegration() {
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1)
                .setVpSize(3, 3);

        Camera camera = cameraBuilder.build();

        // TC01: Small triangle - 1 intersection point
        Triangle triangle = new Triangle(
                new Point(0, 1, -2),
                new Point(1, -1, -2),
                new Point(-1, -1, -2)
        );
        assertEquals(1, countIntersections(camera, triangle),
                "Wrong number of intersections - small triangle");

        // TC02: Larger triangle - 2 intersection points
        triangle = new Triangle(
                new Point(0, 20, -2),
                new Point(1, -1, -2),
                new Point(-1, -1, -2)
        );
        assertEquals(2, countIntersections(camera, triangle),
                "Wrong number of intersections - larger triangle");
    }
}