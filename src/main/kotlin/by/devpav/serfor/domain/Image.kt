package by.devpav.serfor.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "images")
class Image : BasicEntity {

    @Column(name = "image_original_name")
    var originalName: String? = null

    @Column(name = "image_virtual_name")
    var virtualName: String? = null

    @Column(name = "image_length")
    var length: Long? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "image_virtual_directory")
    @JsonBackReference("image_virtual_directory")
    var virtualDirectory: VirtualDirectory? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JsonBackReference("image_image_parent")
    var parentImage: Image? = null

    constructor()

    constructor(originalName: String, virtualName: String, length: Long?) {
        this.originalName = originalName
        this.virtualName = virtualName
        this.length = length
    }

    constructor(originalName: String,
                virtualName: String,
                length: Long?,
                virtualDirectory: VirtualDirectory,
                parentImage: Image) {
        this.originalName = originalName
        this.virtualName = virtualName
        this.length = length
        this.virtualDirectory = virtualDirectory
        this.parentImage = parentImage
    }

    constructor(virtualName: String, length: Long?) {
        this.virtualName = virtualName
        this.length = length
    }

    constructor(virtualName: String, length: Long?, virtualDirectory: VirtualDirectory) {
        this.virtualName = virtualName
        this.virtualDirectory = virtualDirectory
        this.length = length
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Image) return false
        if (!super.equals(other)) return false
        val image = other as Image?
        return virtualName == image!!.virtualName &&
                length == image.length &&
                virtualDirectory == image.virtualDirectory
    }

    override fun hashCode(): Int {
        return Objects.hash(super.hashCode(), virtualName, length, virtualDirectory)
    }

}
