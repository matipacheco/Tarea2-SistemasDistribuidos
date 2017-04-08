import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SimpleMoM extends Remote {
	
	public void   suscribir(String id_cliente, String cola) 		throws java.rmi.RemoteException;
	public void   dar_de_baja(String id_cliente, String cola) 	 	throws java.rmi.RemoteException;
	public void   enviar_mensaje(String cola, String mensaje) 	  	throws java.rmi.RemoteException;
	public String recibir_mensaje(String id_cliente, String cola)	throws java.rmi.RemoteException;
}