package by.devpav.serfor.domain.dtos;

public class RealmConfigDTO extends BasicEntityDTO {

    private String realmVirtualDirectory;
    private String realmMaxImageLength;

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
