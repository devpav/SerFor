package by.devpav.serfor.resources

import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO
import org.springframework.http.ResponseEntity

interface VirtualDirectoryResource {

    fun getByRealmName(realmName: String): ResponseEntity<List<VirtualDirectoryDTO>>

}
