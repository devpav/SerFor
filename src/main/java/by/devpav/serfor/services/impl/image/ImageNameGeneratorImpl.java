package by.devpav.serfor.services.impl.image;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageNameGeneratorImpl implements ImageNameGenerator {

    @Override
    public String generate(String originName, int height, int width) {
        return getSalt() + "_" + height + "x" + width + "_" + originName;
    }

    @Override
    public String generate(int height, int width, String extension) {
        return getSalt() + "_" + height + "x" + width + "." + extension;
    }

    @Override
    public String generate(String extension) {
        return getSalt() + "." + extension;
    }

    public String getSalt() {
        return UUID.randomUUID().toString().substring(0, 7);
    }
}
