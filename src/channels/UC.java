package channels;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
//import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import filesystem.*;

public class UC extends FileChunk {

    public void start() {
        try {

            FileChunk obj = new FileChunk(); // Instantiating the implementation class

            RMI stub = (RMI) UnicastRemoteObject.exportObject(obj, 0); // Exporting the object of implementation class

            Registry registry = LocateRegistry.getRegistry(); // Binding the remote object (stub) in the registry

            registry.bind("RMI", stub);

            System.out.println("Running  u  UC localhost");
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
