package by.devpav.serfor.resources.impl

import by.devpav.serfor.domain.dtos.VirtualDirectoryDTO
import by.devpav.serfor.facade.VirtualDirectoryFacade
import by.devpav.serfor.util.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vdirs")
class VirtualDirectoryResourceImpl {

    @Autowired
    private lateinit var virtualDirectory: VirtualDirectoryFacade;


    @GetMapping
    fun getVirtualDirectories(): ResponseEntity<MutableList<VirtualDirectoryDTO>> =
            HttpResponse.responseCollection(virtualDirectory.findAll())

}