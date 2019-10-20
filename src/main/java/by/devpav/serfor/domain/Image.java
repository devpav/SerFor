package by.devpav.serfor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image extends BasicEntity {

    @Column(name = "image_name")
    private String name;

    @Column(name = "image_length")
    private Long length;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_directory")
    @JsonBackReference("image_directory")
    private Directory directory;

    public Image() {
    }

    public Image(String name, Long length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return Objects.equals(getName(), image.getName()) &&
                Objects.equals(getLength(), image.getLength()) &&
                Objects.equals(getDirectory(), image.getDirectory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLength(), getDirectory());
    }
}
