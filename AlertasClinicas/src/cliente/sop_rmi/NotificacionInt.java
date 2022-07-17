package cliente.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import sensor.dto.Sensor;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public interface NotificacionInt extends Remote {

    public void notificarCallback(Sensor objSensor) throws RemoteException;
}
