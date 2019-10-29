package by.devpav.serfor.service.image

import by.devpav.serfor.services.impl.image.ImageRipper
import org.springframework.stereotype.Component

@Component
class ImageRipperImpl : ImageRipper {

    override fun getImageExtension(filename: String): String? {
        val lastIndex = filename.lastIndexOf(".") + 1
        return if (filename.contains(".")) filename.substring(lastIndex) else null
    }

}
