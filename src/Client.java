package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Client {

    private static final int TIMEOUT = 3000;
    private static final int MAX_RETRY = 5;
    private static final int BUFFER_LENGTH = 255;

    public static void main(String argv[]) {

        if((argv.length < 1) || (argv.length >2)) throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
        int port = (argv.length == 2) ? Integer.parseInt(argv[1]) : 6789;
        boolean first_connection = true;


        try{
            InetAddress serverAddress = InetAddress.getByName(argv[0]);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String msg = stdIn.readLine();
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println(("Socket created, IP: " + socket.getInetAddress() + " ,port: " + socket.getPort()));


            while(true){
                if(msg.equals(".")) break;

                /*  DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverAddress, port);
                    socket.send(request); */
                sendPacket(socket, msg, serverAddress, port);

                /*  DatagramPacket response = new DatagramPacket(new byte[byToSend.length], byToSend.length);
                    socket.receive(response); */
                DatagramPacket response = recievePacket(socket);

                if(first_connection){
                    serverAddress = response.getAddress();
                    port = response.getPort();
                    first_connection = false;
                }

                if(response.getAddress() == serverAddress && response.getPort() == port){
                    //TODO Shuff

                    System.out.println("Response from server: " + response.getData().toString());
                    msg = stdIn.readLine();

                }else sendPacket(socket, msg, serverAddress, port);

            }
            DatagramPacket coinversation = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                    serverAddress, port);
            socket.send(coinversation);


        }catch (SocketTimeoutException e) {
            System.out.println("Timeout error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static DatagramPacket recievePacket(DatagramSocket socket){
        DatagramPacket response = new DatagramPacket(new byte[BUFFER_LENGTH], BUFFER_LENGTH);
        try{
            socket.receive(response);
        }catch (IOException e){
            System.out.println("IOException : " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    private static void sendPacket(DatagramSocket socket, String msg, InetAddress address, int port){
        DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, port);
        try {
            socket.send(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}