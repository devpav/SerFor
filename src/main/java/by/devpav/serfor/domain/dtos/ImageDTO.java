package by.devpav.serfor.domain.dtos;

public class ImageDTO extends BasicEntityDTO {

    private String virtualName;
    private String folder;
    private String parent;
    private Long length;

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

}
