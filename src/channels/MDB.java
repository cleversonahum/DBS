package channels;

import java.net.*;
import filesystem.*;
import java.util.Random;

public class MDB extends Channel {
	public MDB(char type, String address, int port, int packetS, String name) {
		super(type, address, port, packetS, name); 
	}
	
	public void select (DatagramPacket dPacket) {
	    String msg = new String(dPacket.getData()); //I believe thaimport filesystem.*;t will change, because here the bytes from the content are converted to String
	    String[] cmd = msg.split("\\s+"); //Splitting message received
	    
	    if(cmd[0].trim().equals("PUTCHUNK")) { //Checks if the message has the command expected
	       Chunk newChunk = new Chunk("ExampleID", 1, 2, ("teste").getBytes()); //Stores the chunk received into this server
            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(400));
            } catch (Exception e) {e.printStackTrace();}

	       sendMessage("Stored BLA BLA BLA", "224.0.0.0", 3781); //Sending Example message of confirmation into MC channel
        }
	    else
	       System.out.println("Command \""+cmd[0]+"\" Not Found");
	    
	}
    
}
