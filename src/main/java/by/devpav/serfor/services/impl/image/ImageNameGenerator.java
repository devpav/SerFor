package by.devpav.serfor.services.impl.image;

public interface ImageNameGenerator {

    String generateImageName(String originName, int height, int width);
    String generateImageName(String originName);
}
