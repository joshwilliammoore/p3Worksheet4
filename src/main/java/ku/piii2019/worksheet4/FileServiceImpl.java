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
import java.util.LinkedHashSet;
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String rootFolder = "test-music-files";
        MediaItem m = new MediaItem();
            
        Set<Set<MediaItem>> result = new LinkedHashSet<>();
        Set<MediaItem> dups1 = new LinkedHashSet<>();
        Set<MediaItem> dups2 = new LinkedHashSet<>();
        Set<String> uniqueFiles = new LinkedHashSet<>();
        Set<String> dupeFiles = new LinkedHashSet<>();
        Set<String> noMeansNo = new LinkedHashSet<>();
        Set<String> fuster = new LinkedHashSet<>();
        
        for(MediaItem item : allMediaItems){
            Path p = Paths.get(item.getAbsolutePath());
            String filename = p.getFileName().toString();
            
            if(!uniqueFiles.contains(filename)){
                uniqueFiles.add(filename);
            }else{
                dupeFiles.add(filename);
            }
        }
        
        for(MediaItem item : allMediaItems){
            Path p = Paths.get(item.getAbsolutePath());
            String filename = p.getFileName().toString();
            
            if(dupeFiles.contains(filename)){
                if(noMeansNo.isEmpty()){
                    noMeansNo.add(filename);
                    dups1.add(item);
                }
                if(!noMeansNo.contains(filename)){
                    dups2.add(item);
                }else{
                    dups1.add(item);
                }
            }
        }
        
        /*for(MediaItem item : allMediaItems){
            Path p = Paths.get(item.getAbsolutePath());
            String filename = p.getFileName().toString();
            
            
            
            if(!temp.contains(filename)){
                temp.add(filename);
                uniqueFiles.add(item.getAbsolutePath());
            }else{
                if(!temp2.contains(filename)){
                    temp2.add(filename);
                    dups1.add(item);
                }else{
                    dups2.add(item);
                }
            }
            
        }*/
        /*for(String i : uniqueFiles){
            m.setAbsolutePath(i);
            Path p = Paths.get(m.getAbsolutePath());
            String n = p.getFileName().toString();
                if(!dups1.toString().contains(i)&&dups1.toString().contains(n)){
                    dups1.add(m);
                }
                if(!dups2.toString().contains(i)&&dups2.toString().contains(n)){
                    dups2.add(m);
                }
            }*/
        
        /*Set<Set<MediaItem>> result = new HashSet<>();
        Set<MediaItem> dups1 = new HashSet<>();
        Set<MediaItem> dups2 = new HashSet<>();
        Set<String> temp = new HashSet<>();
        
        for(MediaItem m : allMediaItems){
            Path p = Paths.get(m.getAbsolutePath());
            String filename = p.getFileName().toString();
            temp.add(filename);
        }
        System.out.println(temp);
        
        for(String s : temp){
            if(!s.contains(filename)){
                
            }
        }*/
        
        System.out.println("Dups1: "+dups1);
        System.out.println("Dups2: "+dups2);
        System.out.println("uniqueFiles: "+uniqueFiles);
        
        result.add(dups1);
        result.add(dups2);
        return result;
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
