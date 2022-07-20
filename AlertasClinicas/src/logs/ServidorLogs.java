package logs;

import logs.utilidades.ServidorConcurrente;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class ServidorLogs {

    public static void main(String[] args) {
        System.out.println("SERVIDOR LOGS");
        System.out.println("");

        System.out.println("Iniciando servidor en el puerto 5000: ");

        ServidorConcurrente servidorLogs = new ServidorConcurrente();
        servidorLogs.inicializarServidor(5000);
        servidorLogs.ejecutarServidor();
    }
}
