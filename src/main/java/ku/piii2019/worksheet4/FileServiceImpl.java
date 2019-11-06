/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet4;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author James
 */
public class FileServiceImpl implements FileService {
    
    static final List<String> ALLOWED_SUFFIXES = Arrays.asList(".mp3");

    @Override
    public Set<MediaItem> getAllMediaItems(String rootFolder) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /**
        *
        * @param file
        * @param attrs
        * @return
        */
        Path p = Paths.get(rootFolder);
        
            if(!p.isAbsolute()){
                Path currentWorkingFolder = Paths.get("").toAbsolutePath();
                rootFolder = Paths.get(currentWorkingFolder.toString(), rootFolder).toString();
            }
            
            Set<MediaItem> result = new HashSet<>();
            
            MediaItem t = new MediaItem();
        SimpleFileVisitor m = new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                
                
                for(String allowedSuffix : ALLOWED_SUFFIXES){
                    if(file.toString().endsWith(allowedSuffix)){
                        result.add(t.setAbsolutePath(file.toString()));
                        System.out.println(file.getFileName());
                    }
                }
                return FileVisitResult.CONTINUE;
                
                
            }   
        };
        
        Set<MediaItem> test = new HashSet<>();
        try{
            Files.walkFileTree(Paths.get(rootFolder), m);
            
        }catch (IOException ex){
            ex.printStackTrace();
            return test;
        }
        System.out.println(result);
        
        return result;
    }

    @Override
    public Set<Set<MediaItem>> getDuplicates(Set<MediaItem> allMediaItems) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<MediaItem> getItemsToRemove(Set<Set<MediaItem>> duplicates) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeFiles(Set<MediaItem> listToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
