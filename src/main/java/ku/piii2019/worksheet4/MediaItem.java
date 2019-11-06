/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.worksheet4;

import java.io.*;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Objects;

/**
 *
 * @author James
 */
public class MediaItem extends SimpleFileVisitor<Path>{

    private String absolutePath;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public MediaItem setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.absolutePath);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MediaItem other = (MediaItem) obj;
        if (!Objects.equals(this.absolutePath, other.absolutePath)) {
            return false;
        }
        return true;
    }
    
    
}
