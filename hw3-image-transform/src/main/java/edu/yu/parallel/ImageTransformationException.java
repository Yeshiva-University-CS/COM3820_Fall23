package edu.yu.parallel;

public class ImageTransformationException extends RuntimeException {
    
    public ImageTransformationException() {
        super();
    }

    public ImageTransformationException(String message) {
        super(message);
    }
    
    public ImageTransformationException(Throwable cause) {
        super(cause);
    }

    public ImageTransformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
