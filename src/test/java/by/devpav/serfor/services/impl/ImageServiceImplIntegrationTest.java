package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Image;
import by.devpav.serfor.services.ImageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class ImageServiceImplIntegrationTest extends AbstractBasicEntityServiceTest<Image> {

    @Autowired
    private ImageService imageService;


    @PostConstruct
    private void init() {
        super.init(imageService);
    }

    @Test
    @Transactional(readOnly = true)
    public void findAll() {
        final List<Image> images = imageService.findAll();
        assertNotNull(images);
    }

    @Test
    @Transactional
    public void create() {
        final Image image = new Image(UUID.randomUUID().toString(), 10000000L);

        final int sizeBefore = imageService.findAll().size();
        imageService.create(image);
        final int sizeAfter = imageService.findAll().size();

        assertNotNull(image.getId());
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    @Transactional
    public void findById() {
        final Image directory = new Image();
        directory.setVirtualName(UUID.randomUUID().toString());

        final Image dir = imageService.create(directory);
        final Optional<Image> foundEntity = imageService.findById(dir.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(foundEntity.get().getId(), directory.getId());
        assertEquals(foundEntity.get().getVirtualName(), directory.getVirtualName());
    }

}