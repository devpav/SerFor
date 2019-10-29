package by.devpav.serfor.services.directories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class SerForDirectoryManagerImpl implements SerForDirectoryManager {

    @Value("${serfor.images.dir}")
    public String commonFolder;

    private static final String homeDirectory = System.getProperty("user.home");


    @Override
    public Path getSerForFolder() {
        return Paths.get(homeDirectory).resolve(commonFolder);
    }

    @Override
    public Path buildImagePath(String imageName) {
        return this.getSerForFolder().resolve(imageName);
    }

    @Override
    public Path getRealmDirectory(String realmName) {
        return this.getSerForFolder().resolve(realmName);
    }

}
