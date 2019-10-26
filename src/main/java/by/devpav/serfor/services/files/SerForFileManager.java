package by.devpav.serfor.services.files;

import java.nio.file.Path;

public interface SerForFileManager {

    public Boolean isExists(String fileName, Path pathDirectory);

    public Long getFileSize(String fileName, Path pathDirectory);

}
