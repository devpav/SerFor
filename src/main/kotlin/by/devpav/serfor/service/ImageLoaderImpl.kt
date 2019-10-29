package by.devpav.serfor.service

import by.devpav.serfor.exceptions.ImageNotFoundException
import by.devpav.serfor.services.impl.image.ImageLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path

@Component
class ImageLoaderImpl : ImageLoader {

    override fun loadImage(pathToFile: Path): Resource {
        val imageNotFoundException = ImageNotFoundException("Image isn't found with name [${pathToFile.fileName}]")

        if (Files.notExists(pathToFile))
            throw imageNotFoundException

        val resource: Resource = UrlResource(pathToFile.normalize().toUri())

        if (!resource.exists()) {
            throw imageNotFoundException
        }

        return resource
    }

}