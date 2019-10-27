package by.devpav.serfor.domain

import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BasicEntity : Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BasicEntity) return false
        val that = other as BasicEntity?
        return id == that!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}
