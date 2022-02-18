package Ejercicio2;

import java.io.IOException;
import java.net.*;
import java.time.LocalTime;

public class ServidorUDP {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Estamos inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT_SERVER);
            String mensaje;
            LocalTime date = LocalTime.now();
            String fecha = date.toString();

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);
            System.out.println("Recibe la peticion del cliente");
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);
            int puertoCliente = peticion.getPort();
            InetAddress address = peticion.getAddress();
            buffer = fecha.getBytes();
            DatagramPacket fechaVuelta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
            socketUDP.send(fechaVuelta);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
