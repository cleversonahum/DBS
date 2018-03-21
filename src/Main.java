import channels.*;

public class Main {

    public static void main(String[] args) {
	    // write your code here
	    System.out.println("Hello World!");
        Channel MC = new MC(2222, 512, "MC");
	Channel MDB = new MDB(2223, 512, "MDB");
	Channel MDR = new MDR(2224, 512, "MDR");
	Channel UC = new UC(2225, 512, "UC");
        MC.start();
	MDB.start();
	MDR.start();
	UC.start();
	    
    }
}
