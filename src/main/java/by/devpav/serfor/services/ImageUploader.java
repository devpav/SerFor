package by.devpav.serfor.services;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.domain.VirtualDirectory;

import java.nio.file.Path;

public interface ImageUploader {

    String upload(String imageName, String directory, int width, int height, byte[] bytes);
    String upload(String imageName, String directory, byte[] bytes);
    Path upload(Image originalImage, VirtualDirectory virtualDirectory);

}
