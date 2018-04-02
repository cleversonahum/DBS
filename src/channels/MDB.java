package channels;

import java.net.*;
import filesystem.*;
import utilities.Message;
import java.util.Random;

public class MDB extends Channel {
	public MDB(char type, String address, int port, int packetS, String name) {
		super(type, address, port, packetS, name);
	}

	public void select (DatagramPacket dPacket) {

		Message M=new Message(dPacket);
	
	    if(M.getMessageType().equals("PUTCHUNK")) { //Checks if the message has the command expected

	       Chunk newChunk = new Chunk(M.getFile_id(), Integer.parseInt(M.getChunkNumber()), Integer.parseInt(M.getReplicationDegree()), M.getBody()); //Stores the chunk received into this server
           try {
                Random random = new Random();
                Thread.sleep(random.nextInt(400));
            } catch (Exception e) {e.printStackTrace();}
           
           String message= M.createResponse("STORED",M.getVersion(),M.getSender_id(),M.getFile_id(),M.getChunkNumber());
           //System.out.println(message);
	       sendMessage(message.getBytes(), "224.0.0.0", 3781); //Sending message of confirmation into MC channel
        }
	    else
	       System.out.println("Command \""+M.getMessageType()+"\" Not Found");

	}

}
