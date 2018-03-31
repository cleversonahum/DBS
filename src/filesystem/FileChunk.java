package filesystem;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import utilities.Message;

public class FileChunk implements RMI {
    private final static String PATH = "data/files/";
    private String fileId;
    
    
    public void printMessage(){
        System.out.println("TESTE3");
    }
    
    public void storeFile(String fileId, byte[] data){
        //System.out.println("Writing Chunk into Disk"); //code line to delete after
        try{
            Path pathChunk = Paths.get(PATH.concat(Message.getHash(fileId)));
            Files.createDirectories(pathChunk.getParent());
            Files.write(pathChunk, data);
            
        }
        catch(Exception e) {e.printStackTrace();} 
    }

}
