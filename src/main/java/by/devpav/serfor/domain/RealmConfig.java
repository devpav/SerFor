package by.devpav.serfor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "realm_configs")
public class RealmConfig extends BasicEntity {

    @Column(name = "directory_realm")
    private String realmDir;

    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "realm_config_id", referencedColumnName = "id")
    private Realm realm;

    public String getRealmDir() {
        return realmDir;
    }

    public void setRealmDir(String realmDir) {
        this.realmDir = realmDir;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RealmConfig)) return false;
        if (!super.equals(o)) return false;
        RealmConfig that = (RealmConfig) o;
        return Objects.equals(getRealmDir(), that.getRealmDir()) &&
                Objects.equals(getRealm(), that.getRealm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRealmDir(), getRealm());
    }

}
