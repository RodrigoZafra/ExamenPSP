package Ejemplo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Servidor {
    final static int PORT = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Estamos inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT);
            String mensaje;
            while (true) {
                //Contenendor para almacenar el mensaje que nos llegue de cliente
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                System.out.println("Recibimos la peticion del cliente");
                mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                int puertoCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();

                mensaje = "Hola desde el servidor";
                buffer = mensaje.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                System.out.println("Enviamos mensaje del servidor al cliente");
                socketUDP.send(respuesta);
                System.out.println("Finalizado la comunicacion UDP");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
