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
            System.out.println("Running");
			server.receive(rPacket);
            String msgReceived = new String(rPacket.getData());
            
//            try {
//                for(int i = 4; i > 0; i--) {
//                    System.out.println("Thread: " + threadName + ", " + i);
//                    // Let the thread sleep for a while.
//                    Thread.sleep(50);
//                }
//            }
//            catch (InterruptedException e) {
//                 System.out.println("Thread " +  threadName + " interrupted.");
//            }
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
