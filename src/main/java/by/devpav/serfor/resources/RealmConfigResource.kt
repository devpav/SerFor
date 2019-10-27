package by.devpav.serfor.resources

import by.devpav.serfor.domain.dtos.RealmConfigDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

interface RealmConfigResource : BasicEntityResource<RealmConfigDTO> {

    fun getRealmConfig(@PathVariable realmName: String): ResponseEntity<RealmConfigDTO>

}
