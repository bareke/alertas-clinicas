package sensor;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensor.dto.Sensor;
import servidor.sop_rmi.gestionServidorInt;
import servidor.sop_rmi.GestionServidorImpl;
import servidor.utilidades.UtilidadesRegistroC;
import servidor.utilidades.UtilidadesRegistroS;
import servidor.dto.IndicadoresDTO;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class ClienteSensores {

    public static IndicadoresDTO objIndicadores = new IndicadoresDTO();
    public static int id = 0;
    public static gestionServidorInt refRemote;

    public static void main(String[] args) throws RemoteException, IOException {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        System.out.println("SENSORES");
        try {

            System.out.println("Iniciando el rmiregistry en la dirección ip 'localhost'");
            direccionIpRMIRegistry = "localhost";
            System.out.println("Iniciando el rmiregistry por el puerto de escucha 5000");
            numPuertoRMIRegistry = 5000;

            refRemote = new GestionServidorImpl();
            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            refRemote = (gestionServidorInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoServidor");

            boolean resultado = true;
            Sensor objSensor = new Sensor();

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
            Timer timer = new Timer();
            TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    try {
                        RegistrarIndicador();
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClienteSensores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            //genera valores aleatorios cada 30 seg para los indicadores
            timer.schedule(tarea, 0, 30000);

        } catch (Exception err) {
            err.getStackTrace();
            System.out.println("Ocurrió un error");
        }
    }

    public static void RegistrarIndicador() throws RemoteException {
        Random r = new Random();
        objIndicadores.setFrecuenciaCardiaca(r.nextInt(90 - 50) + 50);
        objIndicadores.setPresionSistolica(r.nextInt(150 - 100) + 100);
        objIndicadores.setPresionDiastolica(r.nextInt(100 - 60) + 60);
        objIndicadores.setFrecuenciaRespiratoria(r.nextInt(30 - 2) + 2);
        objIndicadores.setTemperatura(r.nextInt(47 - 26) + 26);
        objIndicadores.setOxigeno(r.nextInt(110 - 85) + 85);
        System.out.println("Frecuencia cardiaca: " + objIndicadores.getFrecuenciaCardiaca());
        System.out.println("Presion Sistolica: " + objIndicadores.getPresionSistolica());
        System.out.println("Presion Diastolica: " + objIndicadores.getPresionDiastolica());
        System.out.println("Frecuencia Respiratoria: " + objIndicadores.getFrecuenciaRespiratoria());
        System.out.println("Temperatura: " + objIndicadores.getTemperatura());
        System.out.println("Oxigeno: " + objIndicadores.getOxigeno());

        //los registra
        refRemote.regIndicadores(id, objIndicadores);
    }
}
