import java.rmi.*;
import java.io.*;

public abstract class APICliente {

	String get_id() {

		String id = "";

		try (BufferedReader archivo = new BufferedReader(new FileReader("id_clientes.txt"))) {	
			id = archivo.readLine();
			//archivo.close();
		}
		catch (IOException e) {
			System.err.println("Excepción al leer el archivo de identificadores: " + e.getMessage());
		}

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("id_clientes.txt"), "utf-8"))) {
			int id_actual = Integer.parseInt(id);
   			int next_id    = id_actual + 1;
            writer.write(String.valueOf(next_id));

        }
		catch (IOException e) {
			System.err.println("Excepción al escribir en el archivo de identificadores: " + e.getMessage());
		}		

		return id;
	}



	String listar_colas() {
		
		String cola, lista = "\n[Cliente] Suscripción a Cola:";

		try (BufferedReader archivo = new BufferedReader(new FileReader("colas.txt"))) {	
			cola = archivo.readLine();
			
			while (cola != null) {
				lista = lista.concat("\n[Cliente] [" + cola + "] Cola " + cola);
				cola = archivo.readLine();
			}
			//archivo.close();
		}
		catch (IOException e) {
			System.err.println("Excepción al leer el archivo de colas: " + e.getMessage());
		}
		return lista;
	}



	//	Suscripción a una cola
	void suscribir(String id_cliente, String cola) {
		try {
			SimpleMoM stub = (SimpleMoM) Naming.lookup("rmi://localhost:5000/SD");
			stub.suscribir(id_cliente, cola);
			System.out.println("Suscripción exitosa!");
		}
		catch (Exception e) {
			System.err.println("Excepción al suscribir: " + e.getMessage());
			System.err.println("Usted se esta suscribiendo a una cola que no existe, porfavor suscribase a una cola que exista.");
		}		
	}



	//	Desinscripción a una cola
	void dar_de_baja(String id_cliente, String cola) {
		try {
			SimpleMoM stub = (SimpleMoM) Naming.lookup("rmi://localhost:5000/SD");
			stub.dar_de_baja(id_cliente, cola);
		}
		catch (Exception e) {
			System.err.println("Excepción al desinscribir: " + e.getMessage());
			System.err.println("Porfavor suscribase a una de las colas existentes.");
		}		
	}	



	//	Envío de mensaje a cola suscrita.
	void enviar(String cola, String mensaje) {
		try {
			SimpleMoM stub = (SimpleMoM) Naming.lookup("rmi://localhost:5000/SD");
			stub.enviar_mensaje(cola, mensaje);
			System.out.println("Mensaje enviado exitosamente!");
		}
		catch (Exception e) {
			System.err.println("Excepción al enviar mensaje: " + e.getMessage());
			System.err.println("Usted esta en una cola que no existe, porfavor elija la opcion [3] para suscribirse a otra cola.");
		}
	}



	//	Recepción de mensaje desde la cola suscrita.
	String recibir(String id_cliente, String cola) {		
		
		String respuesta = "";

		try {
			SimpleMoM stub	 = (SimpleMoM) Naming.lookup("rmi://localhost:5000/SD");
			respuesta = stub.recibir_mensaje(id_cliente, cola);
		}
		catch (Exception e) {
			System.err.println("Excepción al recibir mensaje: " + e.getMessage());
			System.err.println("La cola se encuentra sin mensajes para consumir por ahora.");
			System.err.println();
		}
		return respuesta;
	}
}