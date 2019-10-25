package by.devpav.serfor.domain;

import javax.persistence.*;

@Entity
@Table(name = "realm_configs")
public class RealmConfig extends BasicEntity {

    @Column(name = "realm_virtual_directory")
    private String realmVirtualDirectory;

    @Column(name = "realm_max_image_length", nullable = false)
    private String realmMaxImageLength;

    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "realm_config_id", referencedColumnName = "id")
    private Realm realm;

    public RealmConfig() {
    }

    public RealmConfig(String realmVirtualDirectory, String realmMaxImageLength) {
        this.realmVirtualDirectory = realmVirtualDirectory;
        this.realmMaxImageLength = realmMaxImageLength;
    }


    public String getRealmVirtualDirectory() {
        return realmVirtualDirectory;
    }

    public void setRealmVirtualDirectory(String realmVirtualDirectory) {
        this.realmVirtualDirectory = realmVirtualDirectory;
    }

    public String getRealmMaxImageLength() {
        return realmMaxImageLength;
    }

    public void setRealmMaxImageLength(String realmMaxImageLength) {
        this.realmMaxImageLength = realmMaxImageLength;
    }

}
