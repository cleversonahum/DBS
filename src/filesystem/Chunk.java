package filesystem;

import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Chunk {
    private String fileId;
    private int chunkNumber;
    private int replicationDeg;
    private int contentSize;
    private final static String PATH = "data/chunks/";
    
    public Chunk(String id, int chunkn, int replication,  byte[] body){
        fileId = id;
        chunkNumber = chunkn;
        replicationDeg = replication;
        contentSize = body.length;
        
        //The function will store the chunk content into disk
        storeChunk(body);
    }
    
    public void storeChunk(byte[] data){
        //System.out.println("Writing Chunk into Disk"); //code line to delete after
        try{
            Path pathChunk = Paths.get(PATH.concat(fileId));
            Files.createDirectories(pathChunk.getParent());
            Files.write(pathChunk, data);
            
        }
        catch(Exception e) {e.printStackTrace();} 
    }
    
    public static byte[] getChunk(String id) {
        byte[] rChunk = null;
        try {
            rChunk = Files.readAllBytes(Paths.get(PATH.concat(id)));
        } catch(Exception e) {e.printStackTrace();}
        
        return rChunk;
    }
}