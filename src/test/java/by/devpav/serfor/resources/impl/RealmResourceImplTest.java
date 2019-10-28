package by.devpav.serfor.resources.impl;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.domain.dtos.RealmDTO;
import by.devpav.serfor.facade.mappers.RealmMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@Ignore
public class RealmResourceImplTest extends AbstractBasicEntityResourceTest {

    private static final String URL_RESOURCE = "/api/applications";

    @Autowired
    private RealmMapper realmMapper;


    @Test
    public void findAll() throws Exception {
        final MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.get(URL_RESOURCE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        final MvcResult mvcResult = mvc.perform(mockRequestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        RealmDTO[] applications = super.mapFromJson(content, RealmDTO[].class);

        assertTrue(applications.length > 0);
    }

    @Test
    public void create() throws Exception {
        final String applicationName = "devpav-application";
        final Realm realm = new Realm(applicationName);
        final String mapToJson = mapToJson(realmMapper.toDTO(realm));

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL_RESOURCE)
                .content(mapToJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        final MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        String content = mvcResult.getResponse().getContentAsString();
        RealmDTO createdApplication = super.mapFromJson(content, RealmDTO.class);

        assertNotNull(createdApplication.getId());
        assertEquals(createdApplication.getName(), applicationName);
    }

    @Test
    public void findById() throws Exception {
        final Long entityId = 1L;

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URL_RESOURCE + "/{id}", entityId)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        final MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        final int status = mvcResult.getResponse().getStatus();

        assertEquals(status, 200);

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final Realm returnedRealm = mapFromJson(contentAsString, Realm.class);

        assertNotNull(returnedRealm);
        assertEquals(returnedRealm.getId(), entityId);
    }

    @Test
    public void deleteById() throws Exception {
        final long entityId = 1;

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URL_RESOURCE + "/{id}", entityId)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        final MvcResult resultMvc = mvc.perform(requestBuilder).andReturn();
        final int status = resultMvc.getResponse().getStatus();

        assertEquals(status, HttpStatus.OK.value());
    }

}