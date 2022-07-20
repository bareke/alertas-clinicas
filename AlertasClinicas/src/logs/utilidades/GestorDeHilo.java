package logs.utilidades;

import servidor.utilidades.ConversorJSON;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GestorDeHilo extends Thread {

    private Socket objSocketCliente;
    private List<String> errores = new ArrayList<>();

    public GestorDeHilo(Socket objSocket) {
        this.objSocketCliente = objSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("id del hilo generado:" + Thread.currentThread().getId());
            System.out.println("Atendiendo hilo de cliente");
            DataInputStream flujoEntrada;
            DataOutputStream flujoSalida;
            String message;
            flujoEntrada = new DataInputStream(objSocketCliente.getInputStream());
            flujoSalida = new DataOutputStream(objSocketCliente.getOutputStream());
            message = flujoEntrada.readUTF();//se bloquea el servidor
            String error = ConversorJSON.obtenerLectura(message);

            errores.add(error);
            imprimirErrores();

            objSocketCliente.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void imprimirErrores() {
        for (String error : this.errores) {
            System.out.println(error);
        }
    }
}
