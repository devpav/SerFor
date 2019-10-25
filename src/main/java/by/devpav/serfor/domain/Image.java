package by.devpav.serfor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image extends BasicEntity {

    @Column(name = "image_origin_name")
    private String originName;

    @Id
    @Column(name = "image_origin_name")
    private String virtualName;

    @Column(name = "image_length")
    private Long length;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_virtual_directory")
    @JsonBackReference("image_virtual_directory")
    private VirtualDirectory virtualDirectory;

    public Image() {
    }

    public Image(String originName, String virtualName, Long length) {
        this.originName = originName;
        this.virtualName = virtualName;
        this.length = length;
    }


    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
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
        Image image = (Image) o;
        return Objects.equals(getOriginName(), image.getOriginName()) &&
                Objects.equals(getVirtualName(), image.getVirtualName()) &&
                Objects.equals(getLength(), image.getLength()) &&
                Objects.equals(getVirtualDirectory(), image.getVirtualDirectory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOriginName(), getVirtualName(), getLength(), getVirtualDirectory());
    }

}
