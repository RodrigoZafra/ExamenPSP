package Ejemplo1;

import java.io.IOException;
import java.net.*;

public class Cliente {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket ds = new DatagramSocket();
            String mensaje = "Hola soy el cliente";
            buffer = mensaje.getBytes();
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, address, PORT_SERVER);
            ds.send(peticion);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
