package by.devpav.serfor.domain.dtos;

public class RealmDTO extends BasicEntityDTO {

    private String name;

    private RealmConfigDTO realmConfig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmConfigDTO getRealmConfig() {
        return realmConfig;
    }

    public void setRealmConfig(RealmConfigDTO realmConfig) {
        this.realmConfig = realmConfig;
    }

}
