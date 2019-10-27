package by.devpav.serfor.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "realms")
class Realm : BasicEntity {

    @Column(name = "realm_name", nullable = false, unique = true)
    var name: String? = null

    @OneToMany(mappedBy = "realm", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var directories: Set<VirtualDirectory>? = null

    @OneToOne(mappedBy = "realm", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonBackReference("realm-realm-config")
    var realmConfig: RealmConfig? = null

    constructor() {}

    constructor(name: String) {
        this.name = name
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Realm) return false
        val that = o as Realm?
        return name == that!!.name && directories == that.directories
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name)
    }

}
