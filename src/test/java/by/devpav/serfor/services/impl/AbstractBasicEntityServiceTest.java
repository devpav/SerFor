package by.devpav.serfor.services.impl;

import by.devpav.serfor.SerForApplication;
import by.devpav.serfor.domain.BasicEntity;
import by.devpav.serfor.services.BasicEntityService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SerForApplication.class)
@ActiveProfiles(value = "test")
@Ignore
public class AbstractBasicEntityServiceTest<T extends BasicEntity> {

    private BasicEntityService<T> basicEntityService;


    public void init(final BasicEntityService<T> basicEntityService) {
        this.basicEntityService = basicEntityService;
    }

    @Test
    @Transactional(readOnly = true)
    public void findAll() {
        final List<T> entities = basicEntityService.findAll();
        assertNotNull(entities);
    }

    @Test
    @Transactional
    public void deleteById() {
        final int sizeBeforeDelete = basicEntityService.findAll().size();

        final Long id = basicEntityService.findAll().get(0).getId();

        basicEntityService.deleteById(id);

        final int sizeAfterDelete = basicEntityService.findAll().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

    @Test
    @Transactional
    public void deleteByIds() {
        final List<T> fileList = basicEntityService.findAll();

        Long[] directories = fileList.stream().map(BasicEntity::getId).toArray(Long[]::new);

        basicEntityService.deleteByIds(directories);

        final int sizeAfterDelete = basicEntityService.findAll().size();

        assertEquals(sizeAfterDelete, 0);
    }

}