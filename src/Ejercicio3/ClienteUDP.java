package Ejercicio3;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static int PORT = 5000;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        DatagramSocket ds = null;
        DatagramPacket peticion = null;
        String mensaje = "";
        InetAddress dir_server = null;
        Scanner sc = new Scanner(System.in);

        try {
            ds = new DatagramSocket();
            InetAddress address = InetAddress.getLocalHost();

            while (!mensaje.equals("*")) {
                //Preparamos mensaje para enviar al servidor
                System.out.println("Introduce el mensaje");
                mensaje = sc.nextLine();
                buffer = new byte[1024];
                buffer = mensaje.getBytes();
                peticion = new DatagramPacket(buffer, buffer.length, address, PORT);
                ds.send(peticion);

                DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
                ds.send(respuestaServidor);

                String respuesta = new String(respuestaServidor.getData()).trim();
                respuesta = Integer.toString(respuesta.length());

                if (!respuesta.equals("*")) {
                    System.out.println("Recibida respuesta del servidor" + respuesta);
                } else {
                    System.out.println("Hasta luego");
                }
            }
            ds.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
