/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logs.utilidades;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author
 */
public class ServidorConcurrente {

    private int puerto;
    private ServerSocket serverSocket;
    private Socket objSocket;

    public ServidorConcurrente() {
    }

    public void atenderPeticiones() {
        try {
            while (true) {
                System.out.println("id del hilo principal:" + Thread.currentThread().getId());
                this.objSocket = serverSocket.accept();
                System.out.println("Cliente conectado");
                new GestorDeHilo(this.objSocket).start();
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    public void ejecutarServidor() {
        atenderPeticiones();
    }

    public boolean inicializarServidor(int prmPuerto) {
        try {
            this.puerto = prmPuerto;
            serverSocket = new ServerSocket(puerto);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getObjSocket() {
        return objSocket;
    }

    public void setObjSocket(Socket objSocket) {
        this.objSocket = objSocket;
    }

}
