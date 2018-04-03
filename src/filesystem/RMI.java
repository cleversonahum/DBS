package filesystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import channels.*;

public interface RMI extends Remote{
    void printMessage() throws RemoteException;
    void storeFile(String fileId, int serverID, int repDeg, String addr, int port, byte[] data) throws RemoteException;
    public byte[] getFile(String id) throws RemoteException;
    public void deleteFile(String fileName, int serverID, String address,int port)throws RemoteException;
    //public void deleteChunks (String fileID);
    //public void splitFile(String fileName, int serverID, int repDeg,  String address,int port, MDB mdb);
}
