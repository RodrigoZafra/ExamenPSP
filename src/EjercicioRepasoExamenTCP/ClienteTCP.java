package EjercicioRepasoExamenTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
    static final String HOST = "127.0.0.1";// donde se encuentra el servidor
    static final int PORT = 5000; // el puerto donde se ha conectado el servidor

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String adivinarPalabra = "";
        String cadena = "";

        // para flujo de mensajes, es decir, podamos intercambiar mensajes
        DataInputStream in;
        DataOutputStream out;
        System.out.println("...");

        Socket socket = null;

        try {
            socket = new Socket(HOST, PORT);
            System.out.println("CLIENTE CONECTADO");// ha realizado el accept
            System.out.println("Introduce palabras según la longitud que te voy a dar");

            // Envío y recepcion de mensajes -->CREAMOS FLUJOS
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // READ
            String mensajeRecibido = in.readUTF();
            System.out.println("Mensaje del server: " + mensajeRecibido);
            String[] array = mensajeRecibido.split("-");
            String longitudPalabra = array[1].trim();
            System.out.println("Longitud de la palabra " + longitudPalabra);

            int intentos = Integer.parseInt(longitudPalabra);
            String acertaste = "";
            for (int i = 0; i < intentos; i++) {
                System.out.print("Adivina: ");
                adivinarPalabra = teclado.nextLine();
                System.out.println("Soy cliente y envio: " + adivinarPalabra);
                // WRITE
                out.writeUTF(adivinarPalabra);

                //read
                acertaste = in.readUTF();
                System.out.println("El servidor me responde: " + acertaste);

                if (acertaste.equals("acertaste")) {
                    i = intentos;
                }
                if (acertaste.equals("perdiste")) {
                    System.out.println("Se han acabado los intentos");
                }
            }
            in.close();
            out.close();
            socket.close();
            System.out.println("Hemos cerrado");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
