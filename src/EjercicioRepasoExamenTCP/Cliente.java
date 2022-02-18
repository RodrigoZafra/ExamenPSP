package EjercicioRepasoExamenTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 4000;
    static String mensajeServidor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DataInputStream in;
        DataOutputStream out;

        Socket sk = null;

        try {
            sk = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");

            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());

            mensajeServidor = in.readUTF();
            System.out.println(mensajeServidor);
            while () {
                String palabra = sc.nextLine();
                out.writeUTF(palabra);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
