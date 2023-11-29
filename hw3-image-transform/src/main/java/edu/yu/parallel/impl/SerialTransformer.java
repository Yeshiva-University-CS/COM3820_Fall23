package edu.yu.parallel.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import edu.yu.parallel.ImageTransformer;

public class SerialTransformer implements ImageTransformer {
    private final BufferedImage originalImage;

    public SerialTransformer(BufferedImage originalImage) {
        this.originalImage = originalImage;
    }

    @Override
    public BufferedImage resizeAndAdjustBrightness(double scaleFactor, double brightnessFactor) {

        // Create an empty image of the desired size
        // Determine the new width and height based on the scale factor
        int newWidth = (int) (originalImage.getWidth() * scaleFactor);
        int newHeight = (int) (originalImage.getHeight() * scaleFactor);

        // Create an empty image of the desired size
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        // Draw the original image onto the resized image
        Graphics2D graphics = resizedImage.createGraphics();
        try {
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            // Draw the image, which will be automatically scaled to fit the new size
            graphics.drawImage(originalImage,
                    // dx1, dy1, dx2, dy2 -- are the coordinates of the destination rectangle into
                    // which the image is to be scaled
                    0, 0, resizedImage.getWidth(), resizedImage.getHeight(),
                    // sx1, sy1, sx2, sy2 are the coordinates of the source rectangle that you want
                    // to draw from the original image
                    0, 0, originalImage.getWidth(), originalImage.getHeight(),
                    // no observer needed
                    null);
        } finally {
            graphics.dispose();
        }

        // Adjust brightness
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // Get the original color of the pixel
                Color originalColor = new Color(resizedImage.getRGB(x, y), true);

                // Adjust the brightness of the pixel
                int red = (int) Math.min(255, Math.max(0, originalColor.getRed() * brightnessFactor));
                int green = (int) Math.min(255, Math.max(0, originalColor.getGreen() * brightnessFactor));
                int blue = (int) Math.min(255, Math.max(0, originalColor.getBlue() * brightnessFactor));

                // Set the new color of the pixel
                Color newColor = new Color(red, green, blue, originalColor.getAlpha());
                resizedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        // Return the resized image
        return resizedImage;
    }
}
