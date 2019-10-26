package by.devpav.serfor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "realm_configs")
public class RealmConfig extends BasicEntity {

    @Column(name = "realm_virtual_directory")
    private String realmVirtualDirectory;

    @Column(name = "realm_max_image_length", nullable = false)
    private Integer realmMaxImageLength;

    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "realm_config_id", referencedColumnName = "id")
    private Realm realm;

    public RealmConfig() {
    }

    public RealmConfig(String realmVirtualDirectory, Integer realmMaxImageLength) {
        this.realmVirtualDirectory = realmVirtualDirectory;
        this.realmMaxImageLength = realmMaxImageLength;
    }


    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public String getRealmVirtualDirectory() {
        return realmVirtualDirectory;
    }

    public void setRealmVirtualDirectory(String realmVirtualDirectory) {
        this.realmVirtualDirectory = realmVirtualDirectory;
    }

    public Integer getRealmMaxImageLength() {
        return realmMaxImageLength;
    }

    public void setRealmMaxImageLength(Integer realmMaxImageLength) {
        this.realmMaxImageLength = realmMaxImageLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RealmConfig)) return false;
        if (!super.equals(o)) return false;
        RealmConfig that = (RealmConfig) o;
        return Objects.equals(getRealmVirtualDirectory(), that.getRealmVirtualDirectory()) &&
                Objects.equals(getRealmMaxImageLength(), that.getRealmMaxImageLength()) &&
                Objects.equals(getRealm(), that.getRealm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRealmVirtualDirectory(), getRealmMaxImageLength(), getRealm());
    }

}
