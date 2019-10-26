package by.devpav.serfor.services.files;

public class SerForImageBuilder {

    private Integer width;
    private Integer height;
    private Long size;
    private String virtualName;
    private String originName;

    public SerForImageBuilder height(Integer height) {
        this.height = height;
        return this;
    }

    public SerForImageBuilder virtualName(String virtualName) {
        this.virtualName = virtualName;
        return this;
    }

    public SerForImageBuilder originName(String originName) {
        this.originName = originName;
        return this;
    }


    public SerForImageBuilder size(Long size) {
        this.size = size;
        return this;
    }

    public SerForImageBuilder width(Integer width) {
        this.width = width;
        return this;
    }

    public SerForImage build() {
        return new SerForImageEntity(width, height, size, virtualName, originName);
    }
}
