compilar:
	javac Cliente.java
	javac Servidor.java
	rmic Servidor
Cliente:
	java Cliente
Servidor:
	java Servidor
