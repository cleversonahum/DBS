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

    public byte[] getFile(String id) {
        byte[] rFile = null;
        try {
            rFile = Files.readAllBytes(Paths.get(PATH.concat(Message.getHash(id))));
        } catch(Exception e) {e.printStackTrace();}

        return rFile;
    }
    
   
   /*
   * Deletes the chunks in the respective folders
   * @param fileName Respective file id of the chunks
   */
  public void deleteChunks (String fileID) {

      String filePath= "data/files/";
      File file = new File(filePath);
      File[] listDir = file.listFiles();

        for (int i = 0; i < listDir.length; i++) {

            if (listDir[i].isDirectory() && listDir[i].getName().contains(fileID)) {

              File[] listChunks = listDir[i].listFiles();
               for(int j = 0; j < listChunks.length; j++){
                          listChunks[j].delete();
                           System.out.println("Chunk Deleted");
                     }
                       listDir[i].delete();
            }
        }
  }

}
