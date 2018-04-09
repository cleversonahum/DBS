import filesystem.*;
import utilities.Message;
import channels.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class Client {
    private Client() {}
   public static void main(String[] args) {
      try {
        // Getting the registry 
        Registry registry = LocateRegistry.getRegistry(null);

        // Looking up the registry for the remote object 
        RMI stub = (RMI) registry.lookup("RMI");

        
        	String peerAp = args[0];  //  //host:port
		String[] peer = peerAp.split(":");
		String address=peer[0];
		int port= Integer.parseInt(peer[1]);
		
		
        String subProtocol = args[1];
        
        System.out.println("Adress:"+address);
        System.out.println("Port:"+port);
        System.out.println("subProtocol:"+subProtocol);
        
                
        switch(subProtocol)
		{
		case "BACKUP":
		{
			String fileName = args[2];
			int repD= Integer.parseInt(args[3]);
			System.out.println("filename:"+fileName);
			System.out.println("repDeg:"+repD);
			byte[] rFile=null;
			rFile = Files.readAllBytes(Paths.get(fileName));
			
			
			stub.storeFile(fileName, 1234, repD, address, port, rFile);
			
	        // BACKUP
	       /* char[] chars = new char[1024*1024];
	        Arrays.fill(chars, 'f');
	        String teste = new String(chars);
	        stub.storeFile("./src/teste2", 1234, 2, "224.0.0.1", 3785, teste.getBytes());*/
		}
		break;
		case "DELETE":
		{
			String fileName = args[2];
			stub.deleteFile(fileName,1234,address,port);
			 //stub.deleteFile("teste2",1234,"224.0.0.0",3781);
		}
			 
		break;
		case "RESTORE":
		{
			//RESTORE
			System.out.println("Not fully implemented");
	        //System.out.println(stub.getFile("teste"));
		}
		break;
		case "RECLAIM":
		{
			System.out.println("Not implemented");
		}
		break;
        
		}
        //RESTORE
        //System.out.println(stub.getFile("teste"));
        
        //DELETE
        //stub.deleteFile("teste2",1234,"224.0.0.0",3781);
        
        // System.out.println("Remote method invoked"); 
      } catch (Exception e) {
        System.err.println("Client exception: " + e.toString());
        e.printStackTrace();
      }
   }
}

