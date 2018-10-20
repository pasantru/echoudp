package ejercicio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerImpl extends Thread{
    private byte[] data;
    private InetAddress address;
    private int port;

    public ServerImpl(DatagramPacket dp) {
        this.data = dp.getData();
        this.address = dp.getAddress();
        this.port = dp.getPort();
    }

    @Override
    public void run() {
        try{
            DatagramSocket socket = new DatagramSocket();
            while(true){
                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);
                socket.receive(packet);
                if(packet.getData().toString().equals(".")) break;
                data = packet.getData();

            }
            socket.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
