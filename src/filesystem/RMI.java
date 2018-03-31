package filesystem;

import java.rmi.Remote; 
import java.rmi.RemoteException;

public interface RMI extends Remote{
    void printMessage() throws RemoteException;
    void storeFile(String fileId, byte[] data) throws RemoteException;
}
