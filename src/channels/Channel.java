package channels;

import java.io.*;
import java.net.*;

public class Channel extends Thread {
    private Thread t;
    static DatagramSocket server;
    private String threadName;
    private int packetSize;
    private int portNumber;
    private char serverType; //"m" to multicast and "u" to unicast
    private String address;
   
    public Channel (char type, String addr, int port, int packetS, String name) {
        threadName = name;
        portNumber = port;
        packetSize = packetS;
        serverType = type;
        address = addr;
    }
   
    public void run() {
        
        if(serverType == 'u'){ //Unicast Server
        
            try{
                server = new DatagramSocket(portNumber);
                while(true) {
                    DatagramPacket rPacket = new DatagramPacket(new byte[packetSize], packetSize);
                    System.out.println("Running  "+serverType+"  "+threadName);
        			server.receive(rPacket);
                    String msgReceived = new String(rPacket.getData());
                    
                }
    
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if (serverType == 'm') { //Multicast Server
            
            try (MulticastSocket clientSocket = new MulticastSocket(portNumber)){
                clientSocket.joinGroup(InetAddress.getByName(address));
         
                while (true) {
                    DatagramPacket rPacket = new DatagramPacket(new byte[packetSize], packetSize);
                    System.out.println("Running  "+serverType+"  "+threadName);
                    clientSocket.receive(rPacket);
                    String msgReceived = new String(rPacket.getData());
            }
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Server type not defined");
        }


    }
   
    public void start () {
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
     
    
}
