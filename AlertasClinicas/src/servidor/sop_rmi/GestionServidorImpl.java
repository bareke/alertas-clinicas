package servidor.sop_rmi;

import cliente.sop_rmi.NotificacionInt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import sensor.dto.Sensor;
import servidor.dto.IndicadoresDTO;
/**
 *
 * @author Alejandro Muñoz - 
 */
public class GestionServidorImpl extends UnicastRemoteObject implements gestionServidorInt{
    //Lista de sensores
    private ArrayList<Sensor> sensores ;
    //clientes notificaciones
    private List<NotificacionInt> clientes;

    
    
    public GestionServidorImpl() throws RemoteException, IOException{
        super();
        //lista de sensores
        sensores = new ArrayList<>();
        //clientes notificaciones
        clientes = new ArrayList<>();
    }
    
    //Consultar si existe sensor
    @Override
    public boolean existeSensor(int prmId) throws RemoteException {
        System.out.println("Comprobando si existen sensores en esta habitación...");
        for(int i=0; i<sensores.size(); i++){
            if(sensores.get(i).getId()== prmId){
                
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void regSensor(Sensor objSensor) throws RemoteException{
        System.out.println("Registrando los sensores en la habitacion #"+objSensor.getId()+"...");
        sensores.add(objSensor);
    }
    //Registrar indicadores
    @Override
    public void regIndicadores(int id, IndicadoresDTO objIndicador) throws RemoteException {
        Sensor tempSensor = new Sensor();
        System.out.println("enviando indicadores");
        boolean rango = true;
        for (int i = 0; i < sensores.size(); i++) {
            if (sensores.get(i).getId() == id) {
                        sensores.get(i).setIndicadores(objIndicador);
                        tempSensor = sensores.get(i);
                    }
                }
        rango = evaluarIndicadores(objIndicador);
        if (rango == false) {
            System.out.println("notificando el callback a los clientes notificaciones");
            System.out.println("Alerta en la habitación #"+id);
            
            try {
                //cambiar administrador por cliente notificaciones
                for (int j = 0; j < clientes.size(); j++) {
                    clientes.get(j).notificarCallback(tempSensor);
                }
            } catch (Exception err) {
                err.getStackTrace();
              }
        }  
    }
    
    private boolean evaluarIndicadores(IndicadoresDTO objIndicador){
        int cant = 0;
        if(objIndicador.getFrecuenciaCardiaca() < 60 || objIndicador.getFrecuenciaCardiaca() > 80){
            cant++;
        }
        if(objIndicador.getPresionSistolica() < 110 || objIndicador.getPresionSistolica() > 140){
            cant++;
        }
        if(objIndicador.getPresionDiastolica() < 70 || objIndicador.getPresionDiastolica() > 90){
            cant++;
        }
        if(objIndicador.getFrecuenciaRespiratoria() < 12 || objIndicador.getFrecuenciaRespiratoria() > 20){
            cant++;
        }
        if(objIndicador.getTemperatura() < 36 || objIndicador.getTemperatura() > 37){
            cant++;
        }
        if(objIndicador.getOxigeno() < 95 || objIndicador.getOxigeno() > 100){
            cant++;
        }
        if(cant >= 2){
            return false;
        }
        return true;
    }

    @Override
    public boolean regCliente(NotificacionInt objCliente) throws RemoteException {
        if(clientes.contains(objCliente)==false){
            clientes.add(objCliente);
            System.out.println("Cliente de Notificaciones registrado");
            return true;
        }        
        return false;
    }
    



}
