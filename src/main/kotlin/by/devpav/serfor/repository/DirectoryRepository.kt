package by.devpav.serfor.repository

import by.devpav.serfor.domain.VirtualDirectory
import org.springframework.stereotype.Repository

@Repository
interface DirectoryRepository : BasicEntityRepository<VirtualDirectory> {

    fun findByName(name: String): VirtualDirectory

    fun findByRealm_Name(realmName: String): List<VirtualDirectory>

    fun findByRealm_NameAndWidthAndHeight(realm: String, width: Int?, height: Int?): VirtualDirectory

}
