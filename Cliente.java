import java.io.*;

public class Cliente extends APICliente {
	
	public static void main(String[] args) {	

		String	lista, cola, opcion,
				mensaje, id_cliente;
		Cliente cliente   	 = new Cliente();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			id_cliente = cliente.get_id();

			lista = cliente.listar_colas();
			System.out.println(lista);

			cola = input.readLine();
			cliente.suscribir(id_cliente, cola);

			while (true) {
				System.out.println();
				System.out.println("[Cliente] Seleccionar opción");
				System.out.println("[Cliente] [1] Enviar mensaje");
				System.out.println("[Cliente] [2] Recibir mensaje");
				System.out.println("[Cliente] [3] Suscribir a otra cola");

				opcion = input.readLine();

				switch (opcion) {
					case "1":
						System.out.println();
						System.out.println("[Cliente] Escribir mensaje a enviar:");					
						
						mensaje = input.readLine();
						cliente.enviar(cola, mensaje);
						break;
					
					case "2":
						mensaje = cliente.recibir(id_cliente, cola);
						System.out.println("[Cliente] Mensaje recibido:");
						System.out.println("[Cliente] " + mensaje);
						break;

					case "3":
						cliente.dar_de_baja(id_cliente, cola);

						System.out.println();
						System.out.println(lista);

						cola = input.readLine();
						cliente.suscribir(id_cliente, cola);
						break;
				}				
			}
		}
		catch (IOException e) {
			System.err.println("Excepción de ejecución: " + e.getMessage());
        	System.out.println();
        	e.printStackTrace();
    	}
	}
}