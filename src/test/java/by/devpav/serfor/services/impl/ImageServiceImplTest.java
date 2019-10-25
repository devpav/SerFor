package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.repository.ImageRepository;
import by.devpav.serfor.services.VirtualDirectoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

    @Mock
    private VirtualDirectoryService virtualDirectoryService;

    @Mock
    private ImageRepository imageRepository;

    @Test
    public void create() {
    }

    @Test
    public void uploadOriginalImage() {

    }

    @Test
    public void getResizedImage() {
        ImageServiceImpl imageService = new ImageServiceImpl(imageRepository);

        Image image = imageService.getResizedImage(
                "test-realm",
                "image.png",
                400,
                500
        );

        assertNotNull(image);
    }
}
