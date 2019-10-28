package by.devpav.serfor.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "virtual_directories")
class VirtualDirectory : BasicEntity {

    @Column(name = "directory_origin")
    var origin: Boolean? = null

    @Column(name = "directory_name")
    var name: String? = null

    @ManyToOne
    @JoinColumn(name = "directory_application")
    @JsonBackReference("directory-application")
    var realm: Realm? = null

    @OneToMany(mappedBy = "virtualDirectory", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: Set<Image>? = null

    @Column(name = "directory_width")
    var width: Int? = null

    @Column(name = "directory_height")
    var height: Int? = null

    constructor() {}

    constructor(name: String, width: Int?, height: Int?) {
        this.name = name
        this.width = width
        this.height = height
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VirtualDirectory) return false
        val virtualDirectory = other as VirtualDirectory?
        return name == virtualDirectory!!.name &&
                realm == virtualDirectory.realm &&
                images == virtualDirectory.images &&
                width == virtualDirectory.width &&
                height == virtualDirectory.height
    }

    override fun hashCode(): Int {
        return Objects.hash(id, height, width, name)
    }
}
