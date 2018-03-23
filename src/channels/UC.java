package channels;

import java.net.*;
import filesystem.*;

public class UC extends Channel {
	public UC(char type, String address, int port, int packetS, String name) {
		super(type, address, port, packetS, name); 
	}
    
    public void select (DatagramPacket dPacket) {
	    String msg = new String(dPacket.getData()); //I believe thaimport filesystem.*;t will change, because here the bytes from the content are converted to String
	    String[] cmd = msg.split("\\s+"); //Splitting message received
	    
	    switch(cmd[0].trim()) {
	        case "BACKUP":
	           backupFile();
	           break;
	        
	        case "RESTORE":
	           restoreFile();
	           break;
	           
	        case "DELETE":
	           deleteFile();
	           break;
	        
	        //Add other cases here
	        
            default:
                System.out.println("Comand Not Found");
                break;
	    }
	    if(cmd[0].trim().equals("PUTCHUNK")) { //Checks if the message has the command expected
	       
        }
	    else
	       System.out.println("Command \""+cmd[0]+"\" Not Found");
	    
	}
	
	private void backupFile(){
	    
	}
	
	private void restoreFile(){
	    
	}
	
	private void deleteFile() {
	    
	}
	
	private void manageLocalStore() {
	    
	}
	
	
}
