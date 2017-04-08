import java.rmi.*;
import java.rmi.server.*;

import java.util.*;
import java.io.*;

public class Servidor extends UnicastRemoteObject implements SimpleMoM {
	
	private static HashMap<String, HashMap<String, LinkedList<String>>> colas;
	//	El hash colas tiene como clave el número de cola, y como valor otro hash, 
	//	el cual, a su vez, tiene como clave el id del cliente y como valor la cola
	//	(Queue) con los mensajes.

	//	se necesita que la variable sea global para poder acceder a ella con los métodos,
	//	sin necesidad de pasarla como parámetro.

	public Servidor() throws RemoteException {
		super();
	}



	//	Registrar el objeto remoto (servidor) en el registro RMI.
	public void registrar() {
		try {
			Naming.rebind("rmi://localhost:5000/SD", this);
		}
		catch (Exception e) {
			System.err.println("Excepción al registrar el servidor: " + e.getMessage());
		}		
	}



	//	Suscripción a una cola
	public void suscribir(String id_cliente, String cola) {
		LinkedList<String> cola_mensajes = new LinkedList<String>();
		colas.get(cola).put(id_cliente, cola_mensajes);
	}



	//	Desinscripción a una cola
	public void dar_de_baja(String id_cliente, String cola) {
		colas.get(cola).remove(id_cliente);
	}	



	//	Implementación de método remoto que permite el encolamiento/envío de mensaje a una cola específica
	public void enviar_mensaje(String cola, String mensaje) {
		for (String id_cliente : colas.get(cola).keySet()) {
			colas.get(cola).get(id_cliente).add(mensaje);
		}
	}



	//	Implementación de método remoto que permite sacar/recibir un mensaje a una cola específica
	public String recibir_mensaje(String id_cliente, String cola) {
		return colas.get(cola).get(id_cliente).remove();
	}



	public static void main(String[] args) {

		String linea;
		colas = new HashMap<String, HashMap<String, LinkedList<String>>>();

		try (BufferedReader archivo = new BufferedReader(new FileReader("colas.txt"))) {	
			linea = archivo.readLine();
			
			while (linea != null) {
				HashMap<String, LinkedList<String>> hash = new HashMap<String, LinkedList<String>>();
				colas.put(linea, hash);

				linea = archivo.readLine();
			}
			//archivo.close();
		}
		catch (IOException e) {
			System.err.println("Excepción al leer el archivo de colas: " + e.getMessage());
		}

		try {
			Servidor servidor = new Servidor();
			servidor.registrar();
		}
		catch (IOException e) {
			System.err.println("Excepción de ejecución: " + e.getMessage());
		}
	}
}