package channels;

import java.net.*;
import filesystem.*;
import java.util.Random;

public class MC extends Channel {
	public MC(char type, String address, int port, int packetS, String name) {
		super(type, address, port, packetS, name); 
	}
	
	public void select (DatagramPacket dPacket) {
	    String msg = new String(dPacket.getData());
	    String[] cmd = msg.split("\\s+"); //Splitting message received
	    
	    if(cmd[0].trim().equals("DELETE")) { //Checks if the message has the command expected
	       //Here some Function to Delete the file and all chunks related to it
	       System.out.println("Delete chunk function called");
	       
            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(400));
            } catch (Exception e) {e.printStackTrace();}

	       sendMessage("Removed BLA BLA BLA", "224.0.0.0", 3781); //Sending Example message of confirmation into MC channel
        }
	    else
	       System.out.println("Command \""+cmd[0]+"\" Not Found");
	    
	}
    
}
