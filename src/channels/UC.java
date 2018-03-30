package channels;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
//import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import filesystem.*;

public class UC extends FileChunk {
    
    public void start() {
        System.out.println("TESTEEEEEE");
        try {
            // Instantiating the implementation class 
            FileChunk obj = new FileChunk();

            // Exporting the object of implementation class  
            //System.out.println("Ta chegando antes");
            RMI stub = (RMI) UnicastRemoteObject.exportObject(obj, 0);
            //System.out.println("depois");

            // Binding the remote object (stub) in the registry 
            Registry registry = LocateRegistry.getRegistry();
            //System.out.println("aqui não");

            registry.bind("RMI", stub);
            //System.out.println(" e aqui");
            System.err.println("Server ready");
        }
        catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
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
