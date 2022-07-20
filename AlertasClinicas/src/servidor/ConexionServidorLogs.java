package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import servidor.utilidades.ConversorJSON;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class ConexionServidorLogs {

    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private Socket socket;

    public void conectarseServidor() {
        try {
            this.socket = new Socket("127.0.0.1", 5000);
            this.flujoEntrada = new DataInputStream(socket.getInputStream());
            this.flujoSalida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("error de connexion: " + e.getMessage());
        }
    }

    public void enviarInformacion(String error) throws IOException {
        System.out.println("Enviando información de log: ");
        try {
            String logJson = ConversorJSON.convertirJson(error);
            flujoSalida.writeUTF(logJson);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
