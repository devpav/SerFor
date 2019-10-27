package by.devpav.serfor.resources

import by.devpav.serfor.domain.dtos.RealmDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface RealmResource : BasicEntityResource<RealmDTO> {
    fun getRealms(): ResponseEntity<List<RealmDTO>>
    fun createRealm(@RequestBody realm: RealmDTO): ResponseEntity<RealmDTO>
    fun updateRealm(@RequestBody realm: RealmDTO): ResponseEntity<RealmDTO>
    fun getRealmByName(@PathVariable realmName: String): ResponseEntity<RealmDTO>
}
