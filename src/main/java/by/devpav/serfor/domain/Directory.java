package by.devpav.serfor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "directories")
public class Directory extends BasicEntity {

    @Column(name = "directory_origin")
    private Boolean origin;

    @Column(name = "directory_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "directory_application")
    @JsonBackReference("directory-application")
    private Realm realm;

    @OneToMany(
            mappedBy = "directory",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Image> images;

    @Column(name = "directory_width")
    private Integer width;

    @Column(name = "directory_height")
    private Integer height;

    public Directory() {
    }

    public Directory(String name, Integer width, Integer height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public Boolean getOrigin() {
        return origin;
    }

    public void setOrigin(Boolean origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Directory)) return false;
        Directory directory = (Directory) o;
        return Objects.equals(getName(), directory.getName()) &&
                Objects.equals(getRealm(), directory.getRealm()) &&
                Objects.equals(getImages(), directory.getImages()) &&
                Objects.equals(getWidth(), directory.getWidth()) &&
                Objects.equals(getHeight(), directory.getHeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeight(), getWidth(), getName());
    }
}
