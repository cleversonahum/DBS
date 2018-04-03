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
        fileId = id+"-"+chunkn;
        System.out.println("FILED ID: " +fileId);
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
    
    /*
     * Deletes the chunks in the respective folders
     * @param fileName Respective file id of the chunks
     */
    public static void deleteChunks (String fileID) {

        String filePath= "src/data/chunks/";
        File file = new File(filePath);
        File[] listDir = file.listFiles();

          for (int i = 0; i < listDir.length; i++) {
        	
              if (listDir[i].getName().contains(fileID)) {
            	
            	  listDir[i].delete();
              System.out.println("Chunk Deleted");
                        
              }
          }
    }
    
    public static void deleteFiles (String fileID) {
    	 	String filePath= "data/files/";
         File file = new File(filePath);
         File[] listDir = file.listFiles();

           for (int i = 0; i < listDir.length; i++) {
         	
               if (listDir[i].getName().equals(fileID)) {
             	
             	  listDir[i].delete();
             	  System.out.println("File Deleted");
                         
               }
           }
    	
    }
    
}
