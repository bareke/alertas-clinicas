/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.dto.IndicadoresDTO;
import sensor.dto.Sensor;
import cliente.sop_rmi.NotificacionInt;
/**
 *
 * @author Alejandro Muñoz - 
 */
public interface gestionServidorInt extends Remote {
    //consultar si sensor
    public boolean existeSensor(int prmId) throws RemoteException;
    //registrar sensor
    public void regSensor(Sensor objSensor) throws RemoteException;
    //registrar indicadores en el sensor
    public void regIndicadores(int id, IndicadoresDTO objIndicador) throws RemoteException;
    
    
    public boolean regCliente(NotificacionInt objCliente) throws RemoteException;

;}
