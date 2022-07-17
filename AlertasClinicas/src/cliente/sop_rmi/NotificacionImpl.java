/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.sop_rmi;

import cliente.NotificacionClienteInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sensor.dto.Sensor;

/**
 *
 * @author Alejandro Muñoz -
 */
public class NotificacionImpl extends UnicastRemoteObject implements NotificacionInt{
    private NotificacionClienteInt objr;
    public NotificacionImpl(NotificacionClienteInt objCliente) throws  RemoteException{
        super();
        objr=objCliente;
    }

    @Override
    public void notificarCallback(Sensor objSensor) throws RemoteException {
        objr.notificar(objSensor);
    }
    
}
