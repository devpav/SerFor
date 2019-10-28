package by.devpav.serfor.repository

import by.devpav.serfor.domain.Realm
import org.springframework.stereotype.Repository

@Repository
interface RealmRepository : BasicEntityRepository<Realm> {

    fun findByName(name: String): Realm

}
