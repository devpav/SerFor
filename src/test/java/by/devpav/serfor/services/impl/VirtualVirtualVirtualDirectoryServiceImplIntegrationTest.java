package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.VirtualDirectory;
import by.devpav.serfor.services.VirtualDirectoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;


public class VirtualVirtualVirtualDirectoryServiceImplIntegrationTest extends AbstractBasicEntityServiceTest<VirtualDirectory> {

    @Autowired
    private VirtualDirectoryService virtualDirectoryService;


    @PostConstruct
    private void init() {
        super.init(virtualDirectoryService);
    }

    @Test
    @Transactional(readOnly = true)
    public void findAll() {
        final List<VirtualDirectory> directories = virtualDirectoryService.findAll();
        assertNotNull(directories);
    }

    @Test
    @Transactional
    public void create() {
        final VirtualDirectory virtualDirectory = new VirtualDirectory(UUID.randomUUID().toString(), 500, 800);

        final int sizeBefore = virtualDirectoryService.findAll().size();
        virtualDirectoryService.create(virtualDirectory);
        final int sizeAfter = virtualDirectoryService.findAll().size();

        assertNotNull(virtualDirectory.getId());
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    @Transactional
    public void findById() {
        final VirtualDirectory virtualDirectory = new VirtualDirectory(UUID.randomUUID().toString(), 500, 800);

        final VirtualDirectory dir = virtualDirectoryService.create(virtualDirectory);
        final Optional<VirtualDirectory> foundEntity = virtualDirectoryService.findById(dir.getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(foundEntity.get().getId(), virtualDirectory.getId());
        assertEquals(foundEntity.get().getName(), virtualDirectory.getName());
        assertEquals(foundEntity.get().getHeight(), virtualDirectory.getHeight());
        assertEquals(foundEntity.get().getWidth(), virtualDirectory.getWidth());
    }

}