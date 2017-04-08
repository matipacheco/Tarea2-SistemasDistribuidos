--------------------------------------------------------------------------------
Instrucciones de compilación y ejecución
--------------------------------------------------------------------------------

Iniciar rmiregistry en el puerto 5000:
				rmiregistry 5000 &

Para compilar los scripts, hacer:
				make compilar

Primero hay que ejecutar el servidor. Para esto, hacer:
				make Servidor

Luego se pueden crear tantos clientes como se quiera, ejecutando:
				make Cliente

--------------------------------------------------------------------------------
Consideraciones y supuestos
--------------------------------------------------------------------------------

Creemos necesario señalar dos cosas.

1) Tanto Cliente como Servidor leen del archivo 'colas.txt'. Servidor 
   lee el archivo para saber cuantas colas debe crear, mientras que 
   Cliente lee el archivo para saber cuantas opciones de colas se deben
   desplegar al momento de que el cliente escoge una cola a la cual suscribirse.

2) Para crear identificadores únicos para cada Cliente, se utiliza el 
   archivo 'id_clientes.txt'. Este archivo contiene un único número entero,
   el cual se le asigna a un cliente que se acaba de instanciar. Una vez 
   asignado este id a ese cliente, se modifica el archivo, sumandole 1 al
   número entero (identificador) recién leído por el cliente, de forma que
   otros clientes que accedan al servicio en el futuro, tengo su propio
   identificador único.
