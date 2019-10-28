package by.devpav.serfor.services.impl.image;

public interface ImageNameGenerator {

    String generate(int height, int width, String extension);
    String generate(String originName);

}
