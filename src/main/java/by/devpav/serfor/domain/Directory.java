package by.devpav.serfor.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "directories")
public class Directory extends BasicEntity {

    @Column(name = "directory_name")
    private String name;

    @OneToMany(
            mappedBy = "directory",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Image> images;

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

}
