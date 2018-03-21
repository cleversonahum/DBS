import channels.*;

public class Main {

    public static void main(String[] args) {
        Channel MC = new MC('m',"224.0.0.0",3781, 512, "MC");
        Channel MDB = new MDB('m',"224.0.0.1",3785, 512, "MDB");
        Channel MDR = new MDR('m',"224.0.0.2",3789, 512, "MDR");
        Channel UC = new UC('u',"localhost",3000, 512, "UC");
        MC.start();
        MDB.start();
        MDR.start();
        UC.start();
	    
    }
}
