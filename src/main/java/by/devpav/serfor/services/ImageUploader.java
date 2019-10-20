package by.devpav.serfor.services;

public interface ImageUploader {

    String upload(String imageName, String directory, int width, int height, byte[] bytes);
    String upload(String imageName, String directory, byte[] bytes);

}
