package by.devpav.serfor.config;

import by.devpav.serfor.services.directories.SerForDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ConfiguratorApplication implements ApplicationRunner {

    @Autowired
    private SerForDirectoryManager serForDirectoryManager;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        final Path serForFolder = serForDirectoryManager.getSerForFolder();
        if (Files.notExists(serForFolder)) {
            Files.createDirectory(serForFolder);
        }
    }

}
