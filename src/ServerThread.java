package ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerThread extends Thread{
    private final int BUFFER_SIZE = 255;
    private int port;
    private DatagramSocket ds;

    public ServerThread(int port, DatagramSocket ds){
        this.port = port;
    }

    @Override
    public void run() {
        DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        String msg = "";
        while(!msg.equals(".")){
            ds.send();
        }
    }
}
