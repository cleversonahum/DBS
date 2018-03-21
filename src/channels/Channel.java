package channels;

import java.io.*;
import java.net.*;
import java.util.*;

public class Channel extends Thread {
    private Thread t;
    static DatagramSocket server;
    private String threadName;
    private int packetSize;
    private int portNumber;
   
    public Channel (int port, int packetS, String name) {
        threadName = name;
        portNumber = port;
        packetSize = packetS;
    }
   
    public void run() {
        try{
            server = new DatagramSocket(portNumber);
        
        while(true) {
            DatagramPacket rPacket = new DatagramPacket(new byte[packetSize], packetSize);
            System.out.println("Running "+threadName);
			server.receive(rPacket);
            String msgReceived = new String(rPacket.getData());
            
        }

        }
        catch(Exception e){
            
        }

    }
   
    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
     
    
}
