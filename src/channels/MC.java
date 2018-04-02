package channels;

import java.net.*;
import filesystem.*;
import java.util.Random;
import utilities.Message;

public class MC extends Channel {
	public MC(char type, String address, int port, int packetS, String name) {
		super(type, address, port, packetS, name);
	}

	public void select (DatagramPacket dPacket) {

			Message M=new Message(dPacket);

			if(M.getMessageType().equals("DELETE")) { //Checks if the message has the command expected
	       //Here some Function to Delete the file and all chunks related to it
	       System.out.println("Delete chunk function called");

            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(400));
            } catch (Exception e) {e.printStackTrace();}

	       sendMessage("Removed BLA BLA BLA", "224.0.0.0", 3781); //Sending Example message of confirmation into MC channel
        }
	    else
	       System.out.println("Command \""+"\" Not Found");

	}

}
