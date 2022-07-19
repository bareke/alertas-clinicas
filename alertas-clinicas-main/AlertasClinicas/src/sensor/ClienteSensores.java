package sensor;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        System.out.println("SENSORES");
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
                System.out.println("Ingrese el numero de habitación de los sensores");
                id = sensor.utilidades.UtilidadesConsola.leerEntero();
                resultado = refRemote.existeSensor(id);

                if (resultado == true) {
                    System.out.println("Ya existen sensores registrados en esta habitación ingrese nuevamente una habitación");
                }

            } while (resultado == true);

            //Se registra el sensor en la habitacion #(id)
            objSensor.setId(id);
            refRemote.regSensor(objSensor);

            //Tarea repetida: registrar indicadores cada 30 seg en el sensor
            System.out.println("Leyendo indicadores de la habitación #" + id + " (30 seg)");
            leerIndicadores();

        } catch (Exception err) {
            err.getStackTrace();
            System.out.println("Ocurrió un error");
        }
    }

    public static void leerIndicadores() throws RemoteException {
        System.out.println("Dato para Frecuencia cardiaca");
        objIndicadores.setFrecuenciaCardiaca(leerEntero());

        System.out.println("Dato para Presion Arterial");
        objIndicadores.setPresionArterial(leerEntero());

        System.out.println("Dato para Frecuencia Respiratoria");
        objIndicadores.setFrecuenciaRespiratoria(leerEntero());

        System.out.println("Dato para Temperatura");
        objIndicadores.setTemperatura(leerEntero());

        System.out.println("Dato para Oxigeno");
        objIndicadores.setOxigeno(leerEntero());

        System.out.println("Frecuencia cardiaca: " + objIndicadores.getFrecuenciaCardiaca());
        System.out.println("Presion Sistolica: " + objIndicadores.getPresionArterial());
        System.out.println("Frecuencia Respiratoria: " + objIndicadores.getFrecuenciaRespiratoria());
        System.out.println("Temperatura: " + objIndicadores.getTemperatura());
        System.out.println("Oxigeno: " + objIndicadores.getOxigeno());

        refRemote.regIndicadores(id, objIndicadores);
    }
}
