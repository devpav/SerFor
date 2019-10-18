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
        final List<Image> directories = imageService.findAll();
        assertNotNull(directories);
    }

    @Test
    @Transactional
    public void create() {
        final Image directory = new Image();
        directory.setName(UUID.randomUUID().toString());
        final int sizeBefore = imageService.findAll().size();
        imageService.create(directory);
        final int sizeAfter = imageService.findAll().size();

        assertNotNull(directory.getId());
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    @Transactional
    public void findById() {
        final Image directory = new Image();
        directory.setName(UUID.randomUUID().toString());

        final Image dir = imageService.create(directory);
        final Optional<Image> foundEntity = imageService.findById(dir.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(foundEntity.get().getId(), directory.getId());
        assertEquals(foundEntity.get().getName(), directory.getName());
    }

}