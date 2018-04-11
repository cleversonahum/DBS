package filesystem;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import channels.Channel;
import channels.MC;
import channels.MDB;

import java.nio.file.Files;

import utilities.Message;

public class FileChunk implements RMI {
    private final static String PATH = "data/files/";
    private String fileId;


    public void printMessage(){
        System.out.println("TESTE3");
    }

    public void storeFile(String fileId, int serverID, int repDeg, String addr, int port, byte[] data){
        //System.out.println("Writing Chunk into Disk"); //code line to delete after
        try{
            Path pathChunk = Paths.get(PATH.concat(Message.getHash(fileId)));
            Files.createDirectories(pathChunk.getParent());
            Files.write(pathChunk, data);
            
            splitFile(fileId),1234,2,"224.0.0.1",3785);

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
    
   
   
  public void deleteFile(String fileName, int serverID, String address,int port) {
	  MC mc = new MC('m',"224.0.0.0",3781, 80000, "MC");
	  String fileID= Message.getHash(fileName);
	  String header = "DELETE "+ "1.0 " + Integer.toString(serverID) + " " + fileID + " " +"\r\n\r\n";
	  System.out.println(header);
	  mc.sendMessage(header.getBytes(), address, port);
	  
  }
  
  public static void splitFile(String fileName, int serverID, int repDeg,  String address,int port) {
	  MDB mdb = new MDB('m',"224.0.0.1",3785, 80000, "MDB");
	  
	  File f = new File(fileName);
	  
	  int chunkNumber = 0;
	  int chunkSize=64000;
	  
	  String fileID= Message.getHash(fileName);
	  
	  byte[] buffer = new byte[chunkSize];
	 
	  
	//try-with-resources to ensure closing stream
      try (FileInputStream fis = new FileInputStream(f);
           BufferedInputStream bis = new BufferedInputStream(fis)) {

          int bytesAmount = 0;
          while ((bytesAmount = bis.read(buffer)) > 0) {
              //write each chunk of data into separate file with different number in name
              
        	  	chunkNumber++;
            String header = "PUTCHUNK "+ "1.0 " + Integer.toString(serverID) + " " + fileID + " " + Integer.toString(chunkNumber)+ " " + repDeg + " " +"\r\n\r\n";
              
           
            byte[] Header_b = header.getBytes();
              
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
      		outputStream.write(Header_b);
      		outputStream.write(buffer,0,bytesAmount);

      		System.out.println("Msg Sent: "+ header);
      		byte msg[] = outputStream.toByteArray( );
             
      		mdb.sendMessage(msg, address, port);
  
      		 try {
                 Random random = new Random();
                 Thread.sleep(random.nextInt(500-400)+400);
             } catch (Exception e) {e.printStackTrace();}

          }
         
      } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

}
