package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Directory;
import by.devpav.serfor.services.DirectoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;


public class DirectoryServiceImplIntegrationTest extends AbstractBasicEntityServiceTest<Directory> {

    @Autowired
    private DirectoryService directoryService;


    @PostConstruct
    private void init() {
        super.init(directoryService);
    }

    @Test
    @Transactional(readOnly = true)
    public void findAll() {
        final List<Directory> directories = directoryService.findAll();
        assertNotNull(directories);
    }

    @Test
    @Transactional
    public void create() {
        final Directory directory = new Directory();
        directory.setName(UUID.randomUUID().toString());
        final int sizeBefore = directoryService.findAll().size();
        directoryService.create(directory);
        final int sizeAfter = directoryService.findAll().size();

        assertNotNull(directory.getId());
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    @Transactional
    public void findById() {
        final Directory directory = new Directory();
        directory.setName(UUID.randomUUID().toString());

        final Directory dir = directoryService.create(directory);
        final Optional<Directory> foundEntity = directoryService.findById(dir.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(foundEntity.get().getId(), directory.getId());
        assertEquals(foundEntity.get().getName(), directory.getName());
    }

}