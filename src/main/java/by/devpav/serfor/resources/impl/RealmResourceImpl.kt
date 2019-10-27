package by.devpav.serfor.resources.impl

import by.devpav.serfor.domain.dtos.RealmDTO
import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO
import by.devpav.serfor.facade.RealmFacade
import by.devpav.serfor.facade.VirtualDirectoryFacade
import by.devpav.serfor.resources.RealmResource
import by.devpav.serfor.util.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/realms")
class RealmResourceImpl : RealmResource {

    @Autowired
    private lateinit var realmFacade: RealmFacade;

    @Autowired
    private lateinit var virtualDirectoryFacade: VirtualDirectoryFacade;


    @GetMapping("{realmName}/directories")
    fun getDirectoriesByRealmName(@PathVariable realmName: String): ResponseEntity<List<VirtualDirectoryDTO>> =
            HttpResponse.responseCollection(virtualDirectoryFacade.findByRealmName(realmName))

    @GetMapping
    override fun getRealms(): ResponseEntity<List<RealmDTO>> =
            HttpResponse.responseCollection(realmFacade.findAll())

    @PostMapping
    override fun createRealm(@RequestBody realm: RealmDTO): ResponseEntity<RealmDTO> =
            HttpResponse.responseCreatedEntity(realmFacade.create(realm))

    @PutMapping
    override fun updateRealm(@RequestBody realm: RealmDTO): ResponseEntity<RealmDTO> =
            HttpResponse.responseCreatedEntity(realmFacade.update(realm))

    @GetMapping("/{realmName}")
    override fun getRealmByName(@PathVariable realmName: String): ResponseEntity<RealmDTO> =
            HttpResponse.responseEntity(realmFacade.getRealmByName(realmName))



}