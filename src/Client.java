package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Client {

    private static final int TIMEOUT = 3000;

    public static void main(String argv[]) throws IOException {
        if((argv.length < 1) || (argv.length >2)) throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
        int port = (argv.length == 2) ? Integer.parseInt(argv[1]) : 6789;

        try{
            InetAddress serverAddress = InetAddress.getByName(argv[0]);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String msg = stdIn.readLine();
            byte[] byToSend = msg.getBytes();
            DatagramSocket socket = new DatagramSocket(port);

            System.out.println(("Socket created, IP: " + socket.getInetAddress() + " ,port: " + socket.getPort()));

            while(true){
              DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, request_response.getAddress(), request_response.getPort());
              socket.send(request);

              DatagramPacket response = new DatagramPacket(new byte[byToSend.length], byToSend.length);
              socket.receive(request_response);
              port = response.

              if(msg.equals(".")) break;


              msg = stdIn.readLine();
            }
            DatagramPacket coinversation = new DatagramPacket(msg.getBytes(), msg.getBytes().length, request_response.getAddress(), request_response.getPort());
            socket.send(coinversation);
        }catch (SocketTimeoutException e) {
            System.out.println("Timeout error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
