package by.devpav.serfor.services.directories;

import java.nio.file.Path;

public interface SerForDirectoryManager {
    
    Path getSerForFolder();
    
    Path buildImagePath(String imageName);

    Path getRealmDirectory(String realmName);


}
