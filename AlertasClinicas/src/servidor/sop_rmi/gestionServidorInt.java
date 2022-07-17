package servidor.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.dto.IndicadoresDTO;
import sensor.dto.Sensor;
import cliente.sop_rmi.NotificacionInt;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public interface gestionServidorInt extends Remote {

    //consultar si sensor
    public boolean existeSensor(int prmId) throws RemoteException;

    //registrar sensor
    public void regSensor(Sensor objSensor) throws RemoteException;

    //registrar indicadores en el sensor
    public void regIndicadores(int id, IndicadoresDTO objIndicador) throws RemoteException;

    public boolean regCliente(NotificacionInt objCliente) throws RemoteException;
;
}
