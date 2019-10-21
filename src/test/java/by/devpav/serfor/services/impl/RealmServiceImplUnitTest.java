package by.devpav.serfor.services.impl;

import by.devpav.serfor.domain.Realm;
import by.devpav.serfor.exceptions.EntityNotFoundException;
import by.devpav.serfor.exceptions.realm.RealmMustNotContainsNameIsNull;
import by.devpav.serfor.exceptions.realm.RealmWithNameAlreadyExists;
import by.devpav.serfor.repository.RealmRepository;
import by.devpav.serfor.services.RealmService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RealmServiceImplUnitTest {

    @Mock
    private RealmRepository realmRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RealmMustNotContainsNameIsNull.class)
    public void updateRealmWithNullName() {
        final RealmService realmService = new RealmServiceImpl(realmRepository);

        final Realm foundRealm = new Realm("realm-devpav-test");
        foundRealm.setId(1L);

        final Realm entity = new Realm(null);
        entity.setId(1L);

        when(realmRepository.findById(entity.getId())).thenReturn(Optional.of(foundRealm));

        realmService.update(entity);
    }

    @Test(expected = RealmWithNameAlreadyExists.class)
    public void updateRealmWithNameWhichAlreadyExists() {
        final RealmService realmService = new RealmServiceImpl(realmRepository);

        final Realm foundRealm = new Realm("realm-devpav-test");
        foundRealm.setId(1L);

        final Realm entity = new Realm("realm-devpav-test");
        entity.setId(1L);

        when(realmRepository.findById(entity.getId())).thenReturn(Optional.of(foundRealm));
        when(realmRepository.findByName(entity.getName())).thenReturn(new Realm());

        realmService.update(entity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateRealmWithRealmWhichIdNotFound() {
        final RealmService realmService = new RealmServiceImpl(realmRepository);

        final Realm entity = new Realm("realm-devpav-test");
        entity.setId(1L);

        when(realmRepository.findById(entity.getId())).thenReturn(Optional.empty());

        realmService.update(entity);
    }

    @Test
    public void updateWillBeDone() {
        final RealmService realmService = new RealmServiceImpl(realmRepository);
        final Realm foundRealm = new Realm("realm-devpav-test");
        foundRealm.setId(1L);

        final Realm entity = new Realm("realm-devpav-test");
        entity.setId(1L);

        when(realmRepository.findById(entity.getId())).thenReturn(Optional.of(foundRealm));
        when(realmRepository.findByName(entity.getName())).thenReturn(null);

        realmService.update(entity);

        verify(realmRepository, times(1)).save(foundRealm);
    }

}