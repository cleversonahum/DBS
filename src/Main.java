import channels.*;
import filesystem.FileChunk;

public class Main {

    public static void main(String[] args) {
      
        System.setProperty("java.net.preferIPv4Stack", "true");
        Channel MC = new MC('m',"224.0.0.0",3781, 512, "MC");
        Channel MDB = new MDB('m',"224.0.0.1",3785, 512, "MDB");
        Channel MDR = new MDR('m',"224.0.0.2",3789, 512, "MDR");
        UC UC = new UC();
        MC.start();
        MDB.start();
        MDR.start();
        UC.start();
        
        System.out.println("Hello");
        FileChunk.splitFile("teste2",1234,2,"224.0.0.1",3785,(channels.MDB) MDB);
    }
}
