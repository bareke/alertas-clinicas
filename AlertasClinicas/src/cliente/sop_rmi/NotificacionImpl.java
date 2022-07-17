package cliente.sop_rmi;

import cliente.NotificacionClienteInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sensor.dto.Sensor;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 *
 */
public class NotificacionImpl extends UnicastRemoteObject implements NotificacionInt {

    private NotificacionClienteInt objr;

    public NotificacionImpl(NotificacionClienteInt objCliente) throws RemoteException {
        super();
        objr = objCliente;
    }

    @Override
    public void notificarCallback(Sensor objSensor) throws RemoteException {
        objr.notificar(objSensor);
    }

}
