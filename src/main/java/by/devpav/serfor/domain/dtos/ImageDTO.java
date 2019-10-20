package by.devpav.serfor.domain.dtos;

public class ImageDTO extends BasicEntityDTO {

    private String name;
    private Long length;

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

}
