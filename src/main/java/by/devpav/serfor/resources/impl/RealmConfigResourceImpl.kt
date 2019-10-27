package by.devpav.serfor.resources.impl

import by.devpav.serfor.domain.dtos.RealmConfigDTO
import by.devpav.serfor.facade.RealmFacade
import by.devpav.serfor.resources.RealmConfigResource
import by.devpav.serfor.util.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class RealmConfigResourceImpl : AbstractBasicEntityResource<RealmConfigDTO>(), RealmConfigResource {

    @Autowired
    private lateinit var realmFacade: RealmFacade;


    @GetMapping("/{realmName}/config")
    override fun getRealmConfig(@PathVariable realmName: String): ResponseEntity<RealmConfigDTO> =
            HttpResponse.responseEntity(realmFacade.getRealmConfig(realmName))

}