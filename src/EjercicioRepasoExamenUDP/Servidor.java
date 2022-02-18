package EjercicioRepasoExamenUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static int PORT = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        String palabra = "Perro";
        DatagramPacket paqueteCliente = null;
        DatagramPacket paqueteServer = null;
        InetAddress address = null;
        int puertoCliente;
        String mensaje = "";
        String longitud;
        try {
            DatagramSocket sk = new DatagramSocket(PORT);
            while (true) {
                paqueteCliente = new DatagramPacket(buffer, buffer.length);
                sk.receive(paqueteCliente);
                mensaje = new String(paqueteCliente.getData());

                address = paqueteCliente.getAddress();
                puertoCliente = paqueteCliente.getPort();

                buffer = new byte[1024];
                longitud = String.valueOf(palabra.length());
                buffer = longitud.getBytes();
                paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                sk.send(paqueteServer);

                for (int i = 0; i < palabra.length(); i++) {
                    paqueteCliente = new DatagramPacket(buffer, buffer.length);
                    sk.receive(paqueteCliente);
                    mensaje = new String(paqueteCliente.getData());
                    if (!mensaje.equalsIgnoreCase(palabra)) {
                        buffer = new byte[1024];
                        longitud = String.valueOf(palabra.charAt(i));
                        buffer = longitud.getBytes();
                        paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                        sk.send(paqueteServer);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
