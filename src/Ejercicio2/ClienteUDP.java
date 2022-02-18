package Ejercicio2;

import java.io.IOException;
import java.net.*;

public class ClienteUDP {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte[] buffer;

        try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket ds = new DatagramSocket();
            String mensaje = "Dame la fecha y el dia";
            buffer = mensaje.getBytes();
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, address, PORT_SERVER);
            ds.send(peticion);

            DatagramPacket fechaVuelta = new DatagramPacket(buffer, buffer.length);
            ds.receive(fechaVuelta);
            String fecha = new String(fechaVuelta.getData());
            System.out.println(fecha);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
