package servidor;

import java.io.IOException;
import java.rmi.RemoteException;
import servidor.sop_rmi.GestionServidorImpl;
import servidor.utilidades.UtilidadesRegistroS;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class servidorDeObjetos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, IOException {

        GestionServidorImpl refRemota = new GestionServidorImpl();
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Iniciando el rmiregistry en la dirección ip 'localhost'");
        direccionIpRMIRegistry = "localhost";
        System.out.println("iniciando el rmiregistry por el puerto de escucha 6000");
        numPuertoRMIRegistry = 6000;

        UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
        UtilidadesRegistroS.RegistrarObjetoRemoto(refRemota, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoServidor");
        System.out.println("objeto remoto registrado, esperando peticiones...");
    }
}
