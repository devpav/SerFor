package by.devpav.serfor.resources.impl

import by.devpav.serfor.domain.dtos.ImageDTO
import by.devpav.serfor.facade.ImageFacade
import by.devpav.serfor.resources.ImageResource
import by.devpav.serfor.util.HttpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/")
class ImageResourceImpl: AbstractBasicEntityResource<ImageDTO>(), ImageResource {

    @Autowired
    private lateinit var imageFacade: ImageFacade;


    @PostMapping(value = ["{realmName}/original/upload"],
            produces = [MediaType.APPLICATION_JSON_VALUE],
            consumes = [MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE])
    fun uploadOriginImage(@RequestParam("image") multipart: MultipartFile,
                          @PathVariable("realmName") realmName: String): ResponseEntity<ImageDTO> {
        val uploadedImage = imageFacade.uploadOriginalImage(multipart, realmName)
        return HttpResponse.responseEntity(uploadedImage)
    }

    @GetMapping("/{realmName}/{vdir}/{originalName:.+}")
    fun getRealmConfig(@PathVariable("realmName") realmName: String,
                       @PathVariable("vdir") vdir: String,
                       @PathVariable originalName: String,
                       httpServletRequest: HttpServletRequest): ResponseEntity<Resource> {
        val resource = imageFacade.loadImageByName(originalName, realmName, vdir)
        val contentType = httpServletRequest.servletContext.getMimeType(resource.file.absolutePath)
        return HttpResponse.responseResource(resource, contentType)
    }

    @PostMapping(value = ["{realmName}/{originalName:.+}"])
    fun getImageResize(@PathVariable realmName: String,
                       @PathVariable originalName: String,
                       @RequestParam("width") width: Int?,
                       @RequestParam("height") height: Int?): ResponseEntity<ImageDTO> {
        val resizedImage = imageFacade.getResizedImage(realmName, originalName, width, height)
        return HttpResponse.responseEntity(resizedImage)
    }

}