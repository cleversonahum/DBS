import channels.*;
import filesystem.FileChunk;

public class Main {

    public static void main(String[] args) {
      
        System.setProperty("java.net.preferIPv4Stack", "true");
        Channel MC = new MC('m',"224.0.0.0",3781, 80000, "MC");
        Channel MDB = new MDB('m',"224.0.0.1",3785, 80000, "MDB");
        Channel MDR = new MDR('m',"224.0.0.2",3789, 80000, "MDR");
        UC UC = new UC();
        MC.start();
        MDB.start();
        MDR.start();
        UC.start();
        
        System.out.println("Hello");
        FileChunk.deleteFile("teste2",1234,"224.0.0.0",3781,(channels.MC) MC);
    }
}
