package servidor.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.dto.IndicadoresDTO;
import sensor.dto.SensorRepository;
import cliente.sop_rmi.NotificacionInt;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public interface GestionServidorInt extends Remote {

    //consultar si sensor
    public boolean existeSensor(int prmId) throws RemoteException;

    // registrar sensor
    public void regSensor(SensorRepository objSensor) throws RemoteException;

    // registrar indicadores en el sensor
    public void regIndicadores(int id, IndicadoresDTO objIndicador) throws RemoteException;

    // registrar el cliente en el servidor
    public boolean regCliente(NotificacionInt objCliente) throws RemoteException;
;
}
