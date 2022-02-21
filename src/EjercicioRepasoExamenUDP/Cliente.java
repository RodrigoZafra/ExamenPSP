package EjercicioRepasoExamenUDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {
    public static int PORT = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        DatagramSocket sk = null;
        DatagramPacket paqueteServer = null;
        DatagramPacket paqueteCliente = null;
        String mensaje = "";
        String longitudPalabra = "";
        int intentos = 0;
        String mensajeServer = "";
        InetAddress dir_server = null;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Iniciando el cliente");
            sk = new DatagramSocket();
            dir_server = InetAddress.getLocalHost();
            //Mandamos mensaje al servidor para que nos coja la direccion
            mensaje = "Hola servidor";
            buffer = mensaje.getBytes();
            paqueteCliente = new DatagramPacket(buffer, buffer.length, dir_server, PORT);
            sk.send(paqueteCliente);

            //Recibimos el mensaje del servidor
            paqueteServer = new DatagramPacket(buffer, buffer.length);
            sk.receive(paqueteServer);
            mensajeServer = new String(paqueteServer.getData());
            System.out.println("Mensaje del server: " + mensajeServer);
            String[] array = mensajeServer.split("-");
            longitudPalabra = array[1].trim();

            intentos = Integer.parseInt(longitudPalabra);
            for (int i = 0; i < intentos; i++) {
                //Mandamos mensaje al servidor
                buffer = new byte[1024];
                mensaje = sc.nextLine().trim();
                buffer = mensaje.getBytes();
                paqueteCliente = new DatagramPacket(buffer, buffer.length, dir_server, PORT);
                sk.send(paqueteCliente);

                //Recibimos el mensaje del servidor para saber si hemos acertado
                buffer = new byte[1024];
                paqueteServer = new DatagramPacket(buffer, buffer.length);
                sk.receive(paqueteServer);
                mensajeServer = new String(paqueteServer.getData());
                if()
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
