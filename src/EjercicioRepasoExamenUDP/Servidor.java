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
        int contador = 0;
        String mensaje = "";
        String longitud;
        try {
            System.out.println("Arrancando servidor");
            DatagramSocket sk = new DatagramSocket(PORT);
            while (true) {
                contador = 0;
                //Primero el servidor recibe un mensaje vacio del cliente para coger su address y su puerto
                System.out.println("Recibimos el paquete del cliente");
                paqueteCliente = new DatagramPacket(buffer, buffer.length);
                sk.receive(paqueteCliente);
                mensaje = new String(paqueteCliente.getData());

                address = paqueteCliente.getAddress();
                puertoCliente = paqueteCliente.getPort();
                System.out.println("Enviamos numero de letras al cliente");
                buffer = new byte[1024];
                longitud = "Hola cliente - " + String.valueOf(palabra.length());
                buffer = longitud.getBytes();
                paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                sk.send(paqueteServer);

                while (!mensaje.equalsIgnoreCase(palabra) && !(contador == palabra.length())) {
                    //Recibimos el mensaje del cliente
                    buffer = new byte[1024];
                    paqueteCliente = new DatagramPacket(buffer, buffer.length);
                    sk.receive(paqueteCliente);
                    mensaje = new String(paqueteCliente.getData());
                    mensaje = mensaje.trim();
                    System.out.println("Mostramos mensaje del cliente " + mensaje);
                    if (mensaje.equalsIgnoreCase(palabra)) {
                        buffer = new byte[1024];
                        //longitud = "La respueta no es correcta, la siguiente letra es: " + palabra.charAt(contador);
                        longitud = "Ganaste";
                        buffer = longitud.getBytes();
                        paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                        sk.send(paqueteServer);
                        System.out.println("Has acertado");
                    } else {
                        contador++;
                        if (contador == palabra.length()) {
                            buffer = new byte[1024];
                            longitud = "Perdiste";
                            buffer = longitud.getBytes();
                            paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                            sk.send(paqueteServer);
                            System.out.println("Has perdido");
                        } else {
                            buffer = new byte[1024];
                            longitud = "La respueta no es correcta, la siguiente letra es: " + palabra.substring(0, contador);
                            buffer = longitud.getBytes();
                            paqueteServer = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                            sk.send(paqueteServer);
                            System.out.println("Enviamos:" + palabra.charAt(contador - 1));
                        }
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
