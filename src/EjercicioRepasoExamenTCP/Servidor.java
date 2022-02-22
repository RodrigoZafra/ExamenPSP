package EjercicioRepasoExamenTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PUERTO = 5000;

    public static void main(String[] args) {
        // Flujo de mensajes
        ServerSocket servidor = null;
        Socket sc = null;
        int contadorCliente = 1;
        try {
            System.out.println("Inicializado el servidor, esperando al cliente");
            servidor = new ServerSocket(PUERTO);

            while (true) {
                sc = servidor.accept();// aceptando conexion del cliente
                new AtenderCliente(sc, contadorCliente).start();
                contadorCliente++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
