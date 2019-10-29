package by.devpav.serfor.service.image

import by.devpav.serfor.services.impl.image.ImageResizer
import org.springframework.stereotype.Component
import java.awt.Image
import java.awt.image.BufferedImage

@Component
class ImageResizerImpl : ImageResizer {

    override fun resize(img: BufferedImage, height: Int, width: Int): BufferedImage {
        val tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        val resized = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g2d = resized.createGraphics()
        g2d.drawImage(tmp, 0, 0, null)
        g2d.dispose()
        return resized
    }

}
