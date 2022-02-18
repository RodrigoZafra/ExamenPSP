package EjercicioRepasoExamenTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 4000;

    public static void main(String[] args) {
        String palabra = "Perro";
        DataInputStream in = null;
        DataOutputStream out = null;

        ServerSocket servidor = null;
        Socket sk = null;

        try {
            System.out.println("Iniciando el servidor");
            servidor = new ServerSocket(PORT);

            while (true) {
                sk = servidor.accept();
                System.out.println("Comunicacion establecida");

                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                out.writeUTF("Hola cliente" + palabra.length());

                for (int i = 0; i < palabra.length(); i++) {
                    if (!in.readUTF().equals(palabra)) {
                        out.writeUTF(String.valueOf(palabra.charAt(i)));
                    } else {
                        System.out.println("Has acertado");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
