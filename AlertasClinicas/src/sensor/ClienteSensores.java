package sensor;

import java.io.IOException;
import java.rmi.RemoteException;
import sensor.dto.SensorRepository;
import static sensor.utilidades.UtilidadesConsola.leerEntero;
import servidor.sop_rmi.GestionServidorImpl;
import servidor.utilidades.UtilidadesRegistroC;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.dto.IndicadoresDTO;
import servidor.sop_rmi.GestionServidorInt;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class ClienteSensores {

    public static IndicadoresDTO objIndicadores = new IndicadoresDTO();
    public static int id = 0;
    public static GestionServidorInt refRemote;

    public static void main(String[] args) throws RemoteException, IOException {
        System.out.println("CLIENTE SENSOR");
        System.out.println("");

        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        try {

            System.out.println("Iniciando el rmiregistry en la dirección ip 'localhost'");
            direccionIpRMIRegistry = "localhost";
            System.out.println("Iniciando el rmiregistry por el puerto de escucha 6000");
            numPuertoRMIRegistry = 6000;

            refRemote = new GestionServidorImpl();
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            refRemote = (GestionServidorInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoServidor");

            boolean resultado = true;
            SensorRepository objSensor = new SensorRepository();

            do {
                System.out.print("Numero de habitación de los sensores, ");
                id = sensor.utilidades.UtilidadesConsola.leerEntero();
                resultado = refRemote.existeSensor(id);

                if (resultado == true) {
                    System.out.println("Ya existen sensores registrados en esta habitación ingrese nuevamente una habitación");
                }

            } while (resultado == true);

            //Se registra el sensor en la habitacion #(id)
            objSensor.setId(id);
            refRemote.regSensor(objSensor);

            System.out.println("");
            System.out.println("Leyendo indicadores de la habitación #" + id);

            int opcion;
            do {
                leerIndicadores();

                System.out.println("Presione 1 para ingresar más valores al sensor");
                opcion = sensor.utilidades.UtilidadesConsola.leerEntero();
            } while (opcion == 1);

        } catch (Exception err) {
            err.getStackTrace();
            System.out.println("Ocurrió un error");
        }
    }

    public static void leerIndicadores() throws RemoteException {
        System.out.print("Frecuencia cardiaca, ");
        objIndicadores.setFrecuenciaCardiaca(leerEntero());

        System.out.print("Presion Arterial, ");
        objIndicadores.setPresionArterial(leerEntero());

        System.out.print("Frecuencia Respiratoria, ");
        objIndicadores.setFrecuenciaRespiratoria(leerEntero());

        System.out.print("Temperatura, ");
        objIndicadores.setTemperatura(leerEntero());

        System.out.print("Oxigeno, ");
        objIndicadores.setOxigeno(leerEntero());

        refRemote.regIndicadores(id, objIndicadores);
    }
}
