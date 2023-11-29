package edu.yu.parallel;

import java.awt.image.BufferedImage;

public interface ImageTransformer {

    BufferedImage resizeAndAdjustBrightness(double scaleFactor, double brightnessFactor);
    
}