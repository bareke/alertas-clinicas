package cliente;

import cliente.ArchivosGUI.GUINotificacion;
import java.rmi.RemoteException;
import servidor.utilidades.UtilidadesRegistroC;
import cliente.sop_rmi.NotificacionImpl;
import servidor.sop_rmi.GestionServidorInt;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class ClienteNotificacion {

    public static void main(String[] args) throws RemoteException {
        GestionServidorInt refRemota;
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        System.out.println("CLIENTE ADMINISTRADOR");
        try {
            System.out.println("Iniciando el rmiregistry en la dirección ip 'localhost'");
            direccionIpRMIRegistry = "localhost";
            System.out.println("Iniciando el rmiregistry por el puerto de escucha 6000");
            numPuertoRMIRegistry = 6000;
            refRemota = (GestionServidorInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoServidor");

            //Notificaciondecliente
            NotificacionClienteInt objuser = null;

            objuser = new GUINotificacion();

            NotificacionImpl Cliente = new NotificacionImpl(objuser);
            boolean resultdo = refRemota.regCliente(Cliente);

            if (resultdo) {
                System.out.println("Conexion exitosa ");
            }
        } catch (Exception err) {
            err.getStackTrace();
            System.out.println("Ocurrio un error, vuelva a intentarlo");
        }
    }
}
