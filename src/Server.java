package ejercicio1;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static final int PORT = 6789;
    public static final int BUFFER_SIZE = 255;
    public static void main (String argv[]){
        while(true){
            DatagramSocket ds = null;

            try{
                ds = new DatagramSocket(PORT);
                DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

                ds.receive(dp);
                System.out.println(("Client IP: " + dp.getAddress().getHostAddress() +
                        ", Client Port:" + dp.getPort()));
                DatagramSocket dsThread = new DatagramSocket();
                ds.send(dp);
                dp.setLength(BUFFER_SIZE);
                //while


            } catch (SocketException e){
                System.err.println("SocketException: " + e.getMessage());
            } catch (IOException e){
                System.err.println("IOException: " + e.getMessage());
            } finally {
                if(ds != null) ds.close();
            }



//            ServerThread st = new ServerThread(start_port++);
//            st.start();
//            System.out.println("Started a connection with " + start_port++);
        }
    }
}
