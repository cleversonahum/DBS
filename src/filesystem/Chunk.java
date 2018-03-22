package filesystem;

public class Chunk {
    private String fileId;
    private int chunkNumber;
    private int replicationDeg;
    private int contentSize;
    
    public Chunk(String id, int chunkn, int replication,  byte[] body){
        fileId = id;
        chunkNumber = chunkn;
        replicationDeg = replication;
        contentSize = body.length;
        
        //The function will store the chunk content into disk
        storeChunk(body);
    }
    
    public void storeChunk(byte[] data){
        System.out.println("Writing Chunk into Disk"); //code line to delete after
    }
}
