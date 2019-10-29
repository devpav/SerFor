package by.devpav.serfor.service.image

import by.devpav.serfor.services.impl.image.ImageNameGenerator
import org.springframework.stereotype.Component
import java.util.*

@Component
class ImageNameGeneratorImpl : ImageNameGenerator {

    private val salt: String
        get() = UUID.randomUUID().toString().substring(0, 7)

    override fun generate(height: Int, width: Int, extension: String): String {
        return "$salt.$extension"
    }

    override fun generate(extension: String): String {
        return "$salt.$extension"
    }

}
