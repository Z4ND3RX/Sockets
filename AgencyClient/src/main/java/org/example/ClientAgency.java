package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Message;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.TouristPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.rmi.server.LogStream.log;

@Slf4j
public class ClientAgency {

    private static final String HOST = "localhost";
    private static final int PUERTO = 1234;

    private static int contadorId = 0;

    public void registrarPersona(String name, String documentNumber, String email, String
            address, String phoneNumber) {
//Se intenta abrir una conexión a un servidor remoto usando un objeto Socket
        try (Socket socket = new Socket(HOST, PUERTO)) {
//Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//Se crea un Persona con los datos obtenidos desde la ventana

            //Generar ID unico
            String id = UUID.randomUUID().toString();

            Persona persona = Persona.builder()
                    .id(contadorId++)
                    .name(name)
                    .documentNumber(documentNumber)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build();

//Se envía un Message al servidor con los datos de la petición
            out.writeObject(Message.builder()
                    .tipo("agregarPersona")
                    .contenido(persona).build());
//Obtenemos la respuesta del servidor
            Object respuesta = in.readObject();
//Se imprime la respuesta del servidor. Según la respuesta se podría lanzar una excepción
            log.info((String) respuesta);
//Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Persona> listarPersonas() {
//Se intenta abrir una conexión a un servidor remoto usando un objeto Socket
        try (Socket socket = new Socket(HOST, PUERTO)) {
//Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//Se envía un Message al servidor con los datos de la petición
            out.writeObject(Message.builder()
                    .tipo("listarPersonas").build());
//Obtenemos la respuesta del servidor
            Object respuesta = in.readObject();
//Se hace un casting de la respuesta Object a un ArrayList<Persona>
            ArrayList<Persona> list = (ArrayList<Persona>) respuesta;
//Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();
//Se retorna a lista de Personas
            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void registrarReserva(String id_persona, Integer touristPackageId, String date, Integer
            amount_people) {
//Se intenta abrir una conexión a un servidor remoto usando un objeto Socket
        try (Socket socket = new Socket(HOST, PUERTO)) {
//Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//Se crea un Persona con los datos obtenidos desde la ventana

            //Generar ID unico
            String id = UUID.randomUUID().toString();

            Reserva reserva = Reserva.builder()
                    .id(contadorId++)
                    .idPersona(id_persona)
                    .touristPackageId(touristPackageId)
                    .date(date)
                    .amountPeople(amount_people)
                    .build();

//Se envía un Message al servidor con los datos de la petición
            out.writeObject(Message.builder()
                    .tipo("agregarReserva")
                    .contenido(reserva).build());
//Obtenemos la respuesta del servidor
            Object respuesta = in.readObject();
//Se imprime la respuesta del servidor. Según la respuesta se podría lanzar una excepción
            log.info((String) respuesta);
//Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    ArrayList<Reserva> listarReservas() {
        try (Socket socket = new Socket(HOST, PUERTO)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(Message.builder()
                    .tipo("listarReservas").build());

            Object respuesta = in.readObject();

            ArrayList<Reserva> list = (ArrayList<Reserva>) respuesta;

            in.close();
            out.close();

            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    ArrayList<TouristPackage> listarPaquetes() {
        try (Socket socket = new Socket(HOST, PUERTO)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(Message.builder()
                    .tipo("listarTouristPackages").build());

            Object respuesta = in.readObject();

            ArrayList<TouristPackage> list = (ArrayList<TouristPackage>) respuesta;

            in.close();
            out.close();

            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
