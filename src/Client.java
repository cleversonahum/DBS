import filesystem.*;
import channels.*;

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

        // Calling the remote method using the obtained object 
        char[] chars = new char[1024*1024];
        Arrays.fill(chars, 'f');
        String teste = new String(chars);
        stub.storeFile("./src/teste2", 1234, 2, "224.0.0.1", 3785, (channels.MDB) MDB, teste.getBytes());
        //stub.storeFile("./src/teste2", teste.getBytes());
        //System.out.println(stub.getFile("teste"));

        // System.out.println("Remote method invoked"); 
      } catch (Exception e) {
        System.err.println("Client exception: " + e.toString());
        e.printStackTrace();
      }
   }
}
