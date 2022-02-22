package EjercicioRepasoExamenTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    static final int PUERTO = 5000;

    public static void main(String[] args) {

        String mensajeDelCliente = "";

        // Flujo de mensajes
        DataInputStream in = null;
        DataOutputStream out = null;

        ServerSocket servidor = null;
        Socket sc = null;
        String palabra = "perro";
        int contador = 0;


        try {
            System.out.println("Inicializado el servidor, esperando al cliente");
            servidor = new ServerSocket(PUERTO);

            while (true) {

                contador = 0;
                palabra = palabra;

                sc = servidor.accept();// aceptando conexion del cliente
                System.out.println("Comunicacion establecida");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                // WRITE
                out.writeUTF("Hola cliente - " + palabra.length());

                // READ
                mensajeDelCliente = in.readUTF();
                while (!(contador == palabra.length()) && !mensajeDelCliente.equals(palabra)) {
                    // mensajeDelCliente = in.readUTF();
                    contador++;
                    if (contador == palabra.length()) {
                        out.writeUTF("perdiste");
                        System.out.println("Has agotado los intentos");
                        // mensajeDelCliente=palabra;
                        //contador=0;

                    } else {
                        // saca la primera letra
                        System.out.println("Te has equivocado, toma una letra: " + palabra.substring(0, contador));
                        // WRITE
                        out.writeUTF("" + palabra.substring(0, contador));
                        mensajeDelCliente = in.readUTF();
                    }
                }
                if (mensajeDelCliente.equals(palabra)) {
                    /// WRITE
                    out.writeUTF("acertaste");

                    System.out.println("MUY BIEN!, has acertado la palabra " + palabra);
                }
                in.close();
                out.close();
                sc.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
