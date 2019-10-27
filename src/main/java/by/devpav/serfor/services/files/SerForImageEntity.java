package by.devpav.serfor.services.files;

import java.util.Objects;

public class SerForImageEntity implements SerForImage {

    private Integer width;
    private Integer height;
    private Long size;
    private String virtualName;
    private String originName;

    public SerForImageEntity(Integer width, Integer height, Long size, String virtualName, String originName) {
        this.width = width;
        this.height = height;
        this.size = size;
        this.virtualName = virtualName;
        this.originName = originName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }

    public static SerForImageBuilder getBuilder() {
        return new SerForImageBuilder();
    }

    @Override
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SerForImageEntity)) return false;
        SerForImageEntity that = (SerForImageEntity) o;
        return Objects.equals(getWidth(), that.getWidth()) &&
                Objects.equals(getHeight(), that.getHeight()) &&
                Objects.equals(getSize(), that.getSize()) &&
                Objects.equals(getVirtualName(), that.getVirtualName()) &&
                Objects.equals(getOriginName(), that.getOriginName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight(), getSize(), getVirtualName(), getOriginName());
    }
}
