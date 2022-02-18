package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClienteUDP {
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

            DatagramPacket mensajeVuelta = new DatagramPacket(buffer, buffer.length);
            ds.receive(mensajeVuelta);
            System.out.println("Recive la peticion del cliente");
            mensaje = new String(mensajeVuelta.getData());
            System.out.println(mensaje);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
