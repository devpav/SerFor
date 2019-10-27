package by.devpav.serfor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image extends BasicEntity {

    @Column(name = "image_original_name")
    private String originalName;

    @Column(name = "image_virtual_name")
    private String virtualName;

    @Column(name = "image_length")
    private Long length;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_virtual_directory")
    @JsonBackReference("image_virtual_directory")
    private VirtualDirectory virtualDirectory;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference("image_image_parent")
    private Image parentImage;

    public Image() {
    }

    public Image(String originalName, String virtualName, Long length) {
        this.originalName = originalName;
        this.virtualName = virtualName;
        this.length = length;
    }


    public Image(String originalName,
                 String virtualName,
                 Long length,
                 VirtualDirectory virtualDirectory,
                 Image parentImage) {
        this.originalName = originalName;
        this.virtualName = virtualName;
        this.length = length;
        this.virtualDirectory = virtualDirectory;
        this.parentImage = parentImage;
    }

    public Image(String virtualName, Long length) {
        this.virtualName = virtualName;
        this.length = length;
    }

    public Image(String virtualName, Long length, VirtualDirectory virtualDirectory) {
        this.virtualName = virtualName;
        this.virtualDirectory = virtualDirectory;
        this.length = length;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Image getParentImage() {
        return parentImage;
    }

    public void setParentImage(Image parentImage) {
        this.parentImage = parentImage;
    }

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public VirtualDirectory getVirtualDirectory() {
        return virtualDirectory;
    }

    public void setVirtualDirectory(VirtualDirectory virtualDirectory) {
        this.virtualDirectory = virtualDirectory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        if (!super.equals(o)) return false;
        Image image = (Image) o;
        return Objects.equals(getVirtualName(), image.getVirtualName()) &&
                Objects.equals(getLength(), image.getLength()) &&
                Objects.equals(getVirtualDirectory(), image.getVirtualDirectory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVirtualName(), getLength(), getVirtualDirectory());
    }

}
