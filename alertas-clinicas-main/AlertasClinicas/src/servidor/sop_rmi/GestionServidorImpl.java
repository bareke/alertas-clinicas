package servidor.sop_rmi;

import cliente.sop_rmi.NotificacionInt;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import sensor.dto.SensorRepository;
import servidor.dto.IndicadoresDTO;
import servidor.ConexionServidorLogs;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class GestionServidorImpl extends UnicastRemoteObject implements GestionServidorInt {

    //Lista de sensores
    private ArrayList<SensorRepository> sensores;
    //clientes notificaciones
    private List<NotificacionInt> clientes;
    private ConexionServidorLogs conexionServidorLogs;

    public GestionServidorImpl() throws RemoteException, IOException {
        super();
        //lista de sensores
        sensores = new ArrayList<>();
        //clientes notificaciones
        clientes = new ArrayList<>();
        this.conexionServidorLogs = new ConexionServidorLogs();
        this.conexionServidorLogs.conectarseServidor();
    }

    //Consultar si existe sensor
    @Override
    public boolean existeSensor(int prmId) throws RemoteException {
        System.out.println("Comprobando si existen sensores en esta habitación...");
        for (int i = 0; i < sensores.size(); i++) {
            if (sensores.get(i).getId() == prmId) {

                return true;
            }
        }
        return false;
    }

    @Override
    public void regSensor(SensorRepository objSensor) throws RemoteException {
        System.out.println("Registrando los sensores en la habitacion #" + objSensor.getId() + "...");
        sensores.add(objSensor);
    }

    //Registrar indicadores
    @Override
    public void regIndicadores(int id, IndicadoresDTO objIndicador) throws RemoteException {
        SensorRepository tempSensor = new SensorRepository();
        System.out.println("enviando indicadores");
        int rango = 0;
        String error;
        for (int i = 0; i < sensores.size(); i++) {
            if (sensores.get(i).getId() == id) {
                tempSensor.setId(id);
                tempSensor.setIndicadores(objIndicador);
            }
        }
        rango = evaluarIndicadores(objIndicador);
        switch(rango){
            
            case -4:
                error = "Valor incorrecto en el Oxigeno con valor: "+objIndicador.getOxigeno()+" en la habitación "+id;
                System.out.println(error);
                conexionServidorLogs.enviarInformacion(error);
                break;
            case -3:
                error = "Valor incorrecto en la temperatura con valor: "+objIndicador.getTemperatura()+" en la habitación "+id;
                System.out.println(error);
                conexionServidorLogs.enviarInformacion(error);
                break;
            case -2:
                error = "Valor incorrecto en la Frecuencia Respiratoria con valor: "+objIndicador.getFrecuenciaRespiratoria()+" en la habitación "+id;
                System.out.println(error);
                conexionServidorLogs.enviarInformacion(error);
                break;
            case -1:
                error = "Valor incorrecto en la presion Arterial con valor: "+objIndicador.getPresionArterial()+" en la habitación "+id;
                System.out.println(error);
                conexionServidorLogs.enviarInformacion(error);
                break;
            case 0:
                error = "Valor incorrecto en la Frecuencia Cardiaca con valor: "+objIndicador.getFrecuenciaCardiaca()+" en la habitación "+id;
                System.out.println(error);
                conexionServidorLogs.enviarInformacion(error);
                break;
            case 1:
                break;
            case 2:
                System.out.println("notificando el callback a los clientes notificaciones");
                System.out.println("Alerta en la habitación #" + id);
                try {
                    for (int j = 0; j < clientes.size(); j++) {
                        clientes.get(j).notificarCallback(tempSensor);
                    }
                } catch (Exception err) {
                    err.getStackTrace();
                }
                break;
        }
    }

    private int evaluarIndicadores(IndicadoresDTO objIndicador) {
        int cant = 0;
        if(objIndicador.getFrecuenciaCardiaca() <= 0){return 0;}
        if(objIndicador.getPresionArterial() <= 0){return -1;} 
        if(objIndicador.getFrecuenciaRespiratoria() <= 0){return -2;} 
        if(objIndicador.getTemperatura() <= 0){return -3;}
        if(objIndicador.getOxigeno() <=0){return -4;}
        
        if (objIndicador.getFrecuenciaCardiaca() < 60 || objIndicador.getFrecuenciaCardiaca() > 80) {
            cant++;
        }
        if (objIndicador.getPresionArterial() < 110 || objIndicador.getPresionArterial() > 140) {
            cant++;
        } else if (objIndicador.getPresionArterial() < 70 || objIndicador.getPresionArterial() > 90) {
            cant++;
        }
        if (objIndicador.getFrecuenciaRespiratoria() < 12 || objIndicador.getFrecuenciaRespiratoria() > 20) {
            cant++;
        }
        if (objIndicador.getTemperatura() < 36 || objIndicador.getTemperatura() > 37) {
            cant++;
        }
        if (objIndicador.getOxigeno() < 95 || objIndicador.getOxigeno() > 100) {
            cant++;
        }
        if (cant >= 2) {
            return 2;
        }
        return 1;
    }

    @Override
    public boolean regCliente(NotificacionInt objCliente) throws RemoteException {
        if (clientes.contains(objCliente) == false) {
            clientes.add(objCliente);
            System.out.println("Cliente de Notificaciones registrado");
            return true;
        }
        return false;
    }
}
