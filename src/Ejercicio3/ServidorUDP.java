package Ejercicio3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {
    public static int PORT = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteCliente = null;
        InetAddress address = null;
        int puertoCLiente;
        String mensaje = "";
        String longitud;
        try {
            DatagramSocket sk = new DatagramSocket(PORT);

            while (true) {
                //Recibe paquete cliente
                paqueteCliente = new DatagramPacket(buffer, buffer.length);
                sk.receive(paqueteCliente);
                mensaje = new String(paqueteCliente.getData());
                //recoge el puerto y la direccion del cliente
                address = paqueteCliente.getAddress();
                puertoCLiente = paqueteCliente.getPort();
                System.out.println(puertoCLiente);
                buffer = new byte[1024];
                longitud = String.valueOf(mensaje.trim().length());
                buffer = new byte[1024];
                buffer = longitud.getBytes();
                DatagramPacket paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCLiente);
                sk.send(paqueteServer);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
