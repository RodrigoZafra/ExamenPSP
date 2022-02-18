package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Estamos inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT_SERVER);
            String mensaje;
            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                System.out.println("Recive la peticion del cliente");
                mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                InetAddress address = InetAddress.getLocalHost();
                DatagramSocket ds = new DatagramSocket();
                buffer = mensaje.getBytes();
                DatagramPacket mensajeVuelta = new DatagramPacket(buffer, buffer.length, address, PORT_SERVER);
                ds.send(peticion);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
