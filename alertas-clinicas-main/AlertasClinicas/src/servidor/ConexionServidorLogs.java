/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import servidor.utilidades.ConversorJSON;

/**
 *
 * @author
 */
public class ConexionServidorLogs {
    
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private Socket socket;
    
    public  void conectarseServidor(){
        try{
            this.socket = new Socket("127.0.0.3", 5000);
            this.flujoEntrada = new DataInputStream(socket.getInputStream());
            this.flujoSalida = new DataOutputStream(socket.getOutputStream());
        }catch(IOException e){
            System.out.println("error de connexion: "+e.getMessage());
        }
    }
    
    public void enviarInformacion(String err){
        try{
            String logJson = ConversorJSON.obtenerLectura(err);
            flujoSalida.writeUTF(logJson);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
