package by.devpav.serfor.services.directories;

import java.nio.file.Path;

public interface SerForDirectoryManager {
    
    public Path getSerForFolder();
    
    public Path buildImagePath(String imageName);
    
}
