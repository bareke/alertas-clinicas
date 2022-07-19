/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package logs;

import logs.utilidades.ServidorConcurrente;

/**
 *
 * @author 
 */
public class ServidorLogs {

    public static void main(String[] args) {
        
        
        System.out.println("Servidor de Logs");
        System.out.println("\nIniciando servidor en el puerto 5000: ");
        
        ServidorConcurrente servidorLogs = new ServidorConcurrente();
        servidorLogs.inicializarServidor(5000);
        servidorLogs.ejecutarServidor();
    }
}
