package by.devpav.serfor.exceptions.image;

public class ImageAlreadyExistsException extends RuntimeException {

    public ImageAlreadyExistsException() {
    }

    public ImageAlreadyExistsException(String message) {
        super(message);
    }
}
