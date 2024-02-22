package org.example;

import org.example.model.Message;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.TouristPackage;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadClient implements Runnable {

    private final Socket socket;
    private final AgencyServer agencia;

    public ThreadClient(Socket socket, AgencyServer agencia) {
        this.socket = socket;
        this.agencia = agencia;
    }

    @Override
    public void run() {
        try {
//Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//Se lee el Message enviado por el Persona
            Message message = (Message) in.readObject();
//Se captura el tipo de Message
            String tipo = message.getTipo();
//Se captura el contenido del Message
            Object contenido = message.getContenido();
//Según el tipo de Message se invoca el método correspondiente
            switch (tipo) {
                case "agregarPersona":
                    agregarPersona((Persona) contenido, out);
                    break;
                case "listarPersonas":
                    listarPersonas(out);
                    break;
                case "listarTouristPackages":
                    listarPaquetes(out);
                    break;
                case "agregarReserva":
                    agregarReserva((Reserva) contenido, out);
                    break;
                case "listarReservas":
                    listarReservas(out);
                    break;
            }
//Se cierra la conexión del socket para liberar los recursos asociados
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void agregarPersona(Persona persona, ObjectOutputStream out) throws IOException {
        try {
            agencia.agregarPersona(persona);
            out.writeObject("Persona agregado correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }

    public void agregarReserva(Reserva reserva, ObjectOutputStream out) throws IOException {
        try {
            agencia.agregarReserva(reserva);
            out.writeObject("Reserva agregada correctamente");
        }catch (Exception e){
            out.writeObject(e.getMessage());
        }
    }
    public void listarPersonas(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarPersonas());
    }
    public void listarReservas(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarReservas());
    }
    public void listarPaquetes(ObjectOutputStream out) throws IOException {
        out.writeObject(agencia.listarTouristPackages());
    }
}
