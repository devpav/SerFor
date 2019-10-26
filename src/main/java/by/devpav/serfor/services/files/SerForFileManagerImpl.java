package by.devpav.serfor.services.files;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SerForFileManagerImpl implements SerForFileManager {

    @Override
    public Boolean isExists(String fileName, Path pathDirectory) {
        return Files.exists(pathDirectory.resolve(pathDirectory));
    }

    @Override
    public Long getFileSize(String fileName, Path pathDirectory) {
        final Path targetFile = pathDirectory.resolve(pathDirectory);

        try {
            return Files.size(targetFile);
        } catch (IOException e) {
            return -1L;
        }
    }

}
