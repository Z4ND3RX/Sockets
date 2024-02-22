package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.TouristPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Slf4j
public class AgencyServer {
    private ArrayList<Persona> personas;
    private ArrayList<TouristPackage> touristPackages;
    private ArrayList<Reserva> reservas;

    public AgencyServer() {
        this.personas = new ArrayList<>();
        this.touristPackages = new ArrayList<>();
        this.reservas = new ArrayList<>();

        generateTouristPackages();
    }

    public void agregarPersona(Persona persona) throws Exception {
        personas.add(persona);
        log.info("Persona agregada correctamente");
    }

    public ArrayList<Persona> listarPersonas() {
        return personas;
    }

    public ArrayList<TouristPackage> listarTouristPackages() {
        return touristPackages;
    }

    public void agregarReserva(Reserva reserva) throws Exception {
        reservas.add(reserva);
        log.info("Reserva agregada correctamente");
    }

    public ArrayList<Reserva> listarReservas() {
        return reservas;
    }

    public void iniciarEscucha() {
        int puerto = 1234;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            log.info("Esperando conexión...");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                log.info("Cliente conectado");

                ThreadClient hilo = new ThreadClient(clienteSocket, this);
                new Thread(hilo).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateTouristPackages(){
        Random random = new Random();
        for (int i = 0; i < 5; i++){
            TouristPackage tp = new TouristPackage(i, "Paquete " + i , Operations.generateRandomDate(), random.nextInt(11), 500000.0 + (2000000.0 - 500000.0) * random.nextDouble(),
                    Operations.generateRandomCities(3));
            this.touristPackages.add(tp);
        }
        log.info("Paquetes: " + touristPackages);
    }

}
