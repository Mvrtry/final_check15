package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import java.io.File;

/**
 * Unit tests for renderer.ImageWriter class
 * Tests the basic functionality of image generation with a grid pattern
 *
 * @author Maor Atari
 */
public class ImageWriterTest {

    /**
     * Default constructor for ImageWriterTest
     */
    public ImageWriterTest() {
    }

    /**
     * Test method for {@link ImageWriter#writePixel(int, int, Color)} and {@link ImageWriter#writeToImage(String)}.
     * Creates a 800x500 pixel image with a 16x10 grid of alternating colored squares.
     * Each square is 50x50 pixels.
     *
     * Note: Make sure the 'images' directory exists in your project root before running this test.
     */
    @Test
    void testWriteImage() {
        // Ensure images directory exists
        File imagesDir = new File(System.getProperty("user.dir") + "/images");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
        // Image resolution constants
        final int IMAGE_WIDTH = 800;
        final int IMAGE_HEIGHT = 500;

        // Grid constants
        final int GRID_COLUMNS = 16;
        final int GRID_ROWS = 10;

        // Calculate square dimensions
        final int SQUARE_WIDTH = IMAGE_WIDTH / GRID_COLUMNS;   // 50 pixels
        final int SQUARE_HEIGHT = IMAGE_HEIGHT / GRID_ROWS;    // 50 pixels

        // Define contrasting colors for the checkerboard pattern
        final Color YELLOW = new Color(255, 255, 0);    // Bright yellow
        final Color RED = new Color(255, 0, 0);          // Bright red

        // Create ImageWriter instance
        ImageWriter imageWriter = new ImageWriter(IMAGE_WIDTH, IMAGE_HEIGHT);

        // Generate the grid pattern
        for (int row = 0; row < IMAGE_HEIGHT; row++) {
            for (int col = 0; col < IMAGE_WIDTH; col++) {
                // Determine which grid square this pixel belongs to
                int gridRow = row / SQUARE_HEIGHT;
                int gridCol = col / SQUARE_WIDTH;

                // Create checkerboard pattern: alternate colors based on grid position
                Color pixelColor = ((gridRow + gridCol) % 2 == 0) ? YELLOW : RED;

                // Color the pixel (note: writePixel expects (x, y) which is (col, row))
                imageWriter.writePixel(col, row, pixelColor);
            }
        }

        // Generate the PNG file
        System.out.println("Saving image to: " + System.getProperty("user.dir") + "/images/grid_test.png");
        imageWriter.writeToImage("grid_test");
    }

    /**
     * Alternative test method that creates a more visible grid with border lines.
     * This version adds black border lines between squares for better visualization.
     *
     * Note: Make sure the 'images' directory exists in your project root before running this test.
     */
    @Test
    void testWriteImageWithBorders() {
        // Ensure images directory exists
        File imagesDir = new File(System.getProperty("user.dir") + "/images");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
        // Image resolution constants
        final int IMAGE_WIDTH = 800;
        final int IMAGE_HEIGHT = 500;

        // Grid constants
        final int GRID_COLUMNS = 16;
        final int GRID_ROWS = 10;

        // Calculate square dimensions
        final int SQUARE_WIDTH = IMAGE_WIDTH / GRID_COLUMNS;   // 50 pixels
        final int SQUARE_HEIGHT = IMAGE_HEIGHT / GRID_ROWS;    // 50 pixels

        // Define colors
        final Color LIGHT_BLUE = new Color(173, 216, 230);  // Light blue
        final Color DARK_GREEN = new Color(0, 100, 0);       // Dark green
        final Color BLACK = new Color(0, 0, 0);              // Black for borders

        // Create ImageWriter instance
        ImageWriter imageWriter = new ImageWriter(IMAGE_WIDTH, IMAGE_HEIGHT);

        // Generate the grid pattern with borders
        for (int row = 0; row < IMAGE_HEIGHT; row++) {
            for (int col = 0; col < IMAGE_WIDTH; col++) {
                // Check if we're on a border (grid line)
                boolean isVerticalBorder = (col % SQUARE_WIDTH == 0);
                boolean isHorizontalBorder = (row % SQUARE_HEIGHT == 0);

                Color pixelColor;

                if (isVerticalBorder || isHorizontalBorder) {
                    // Draw border lines in black
                    pixelColor = BLACK;
                } else {
                    // Determine which grid square this pixel belongs to
                    int gridRow = row / SQUARE_HEIGHT;
                    int gridCol = col / SQUARE_WIDTH;

                    // Create checkerboard pattern
                    pixelColor = ((gridRow + gridCol) % 2 == 0) ? LIGHT_BLUE : DARK_GREEN;
                }

                // Color the pixel
                imageWriter.writePixel(col, row, pixelColor);
            }
        }

        // Generate the PNG file
        System.out.println("Saving image to: " + System.getProperty("user.dir") + "/images/grid_with_borders_test.png");
        imageWriter.writeToImage("grid_with_borders_test");
    }
}