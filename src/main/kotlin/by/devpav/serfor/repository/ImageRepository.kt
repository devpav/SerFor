package by.devpav.serfor.repository

import by.devpav.serfor.domain.Image
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : BasicEntityRepository<Image> {

    fun findByVirtualName(name: String): Image?

}
