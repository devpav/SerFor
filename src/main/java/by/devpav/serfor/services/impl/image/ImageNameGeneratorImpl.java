package by.devpav.serfor.services.impl.image;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageNameGeneratorImpl implements ImageNameGenerator {

    @Override
    public String generateImageName(String originName, int height, int width) {
        return UUID.randomUUID() + "_" + height + "x" + width + "_" + originName;
    }

    @Override
    public String generateImageName(String originName) {
        return UUID.randomUUID() + "_" + originName;
    }

}
