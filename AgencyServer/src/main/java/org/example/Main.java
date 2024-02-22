package org.example;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Main {
    public static void main(String[] args) {
        int puerto = 1234;
        // Se crea la instancia de la clase principal que contiene toda la lógica del
        // proyecto
        AgencyServer AgencyServer = new AgencyServer();
        // Se crea el ServerSocket en el puerto 1234
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            log.info("Esperando conexión...");
            while (true) {
                // Se obtiene la conexión del cliente
                Socket clienteSocket = serverSocket.accept();
                log.info("Cliente conectado");
                // Se crea un hilo para la conexión del cliente
                ThreadClient hilo = new ThreadClient(clienteSocket, AgencyServer);
                new Thread(hilo).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}