package logs.utilidades;

import servidor.utilidades.ConversorJSON;
import servidor.utilidades.ConversorStringALocalDateTime;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class GestorDeHilo extends Thread{
    private Socket objSocketCliente;
       
    public GestorDeHilo(Socket objSocket){
        this.objSocketCliente = objSocket;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("id del hilo generado:"+Thread.currentThread().getId());
            System.out.println("Atendiendo hilo de cliente");
            DataInputStream flujoEntrada;
            DataOutputStream flujoSalida;
            String message;            
            flujoEntrada=new DataInputStream(objSocketCliente.getInputStream());
            flujoSalida=new DataOutputStream(objSocketCliente.getOutputStream());
            message = flujoEntrada.readUTF();//se bloquea el servidor
            System.out.println("por lo menos llegó el mensaje no se si se imprime :D");
            System.out.println(message);

            String error = ConversorJSON.obtenerLectura(message);
           
            System.out.println(error);
            
            objSocketCliente.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
