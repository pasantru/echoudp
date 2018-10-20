package ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    public static final int PORT = 6789;
    public static final int BUFFER_SIZE = 255;

    public static void main (String argv[]) {
        DatagramSocket ds = null;

        try{
            InetAddress localAddress = InetAddress.getByName("localhost");
            ds = new DatagramSocket(PORT, localAddress);
            DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

            while(true){
                ds.receive(dp);
                System.out.println("New client requesting service");
                (new ServerImpl(dp)).start();
                ds.send(dp);
                dp.setLength(BUFFER_SIZE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ds != null) ds.close();
        }
    }
}
