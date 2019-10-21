package by.devpav.serfor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "realms")
public class Realm extends BasicEntity {

    @Column(name = "realm_name", nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "realm",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Directory> directories;

    @OneToOne(mappedBy = "realm",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)

    @JsonBackReference("realm-realm-config")
    private RealmConfig realmConfig;

    public Realm() {
    }

    public Realm(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(Set<Directory> directories) {
        this.directories = directories;
    }

    public RealmConfig getRealmConfig() {
        return realmConfig;
    }

    public void setRealmConfig(RealmConfig realmConfig) {
        this.realmConfig = realmConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Realm)) return false;
        Realm that = (Realm) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDirectories(), that.getDirectories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
