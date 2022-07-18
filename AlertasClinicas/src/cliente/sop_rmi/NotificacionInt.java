package cliente.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import sensor.dto.SensorRepository;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public interface NotificacionInt extends Remote {

    public void notificarCallback(SensorRepository objSensor) throws RemoteException;
}
