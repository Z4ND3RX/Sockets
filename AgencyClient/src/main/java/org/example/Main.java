package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Persona;
import org.example.model.Reserva;
import org.example.model.TouristPackage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

@Slf4j
public class Main {
    private static ClientAgency clientAgency = new ClientAgency();
    public static void main(String[] args) {
        boolean active = true;

        while (active) {

            String[] opciones = {"Registrarse", "Consultar paquetes", "Generar reserva", "Consultar usuarios", "Consultar reserva","Salir"};

            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Menú de acciones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            // Realiza acciones basadas en la opción seleccionada
            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Por favor digita la siguiente información personal");

                    String name;
                    do{
                        name = JOptionPane.showInputDialog("Ingresa tu nombre");
                    }while(name.isEmpty());

                    String documentNumber;
                    do{
                        documentNumber = JOptionPane.showInputDialog("Ingresa tu número de documento");
                    }while(documentNumber.isEmpty());
                    String email;
                    do{
                        email = JOptionPane.showInputDialog("Ingresa tu email");
                    }while(email.isEmpty());
                    String address;
                    do{
                        address = JOptionPane.showInputDialog("Ingresa tu dirección");
                    }while(address.isEmpty());
                    String phoneNumber;
                    do{
                        phoneNumber = JOptionPane.showInputDialog("Ingresa tu número de teléfono");
                    }while(phoneNumber.isEmpty());
                    clientAgency.registrarPersona(name, documentNumber, email, address, phoneNumber);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Has seleccionado Consultar paquetes");
                    String message = "Lista de paquetes registrados:\n";
                    int count = 1;
                    for (TouristPackage paquete : clientAgency.listarPaquetes()) {
                        message += "Paquete " + count + ":\n" + paquete.toString() + "\n";
                        count++;
                    }
                    JOptionPane.showMessageDialog(null, message);

                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Has seleccionado generar reserva, por favor digita la siguiente información para generarla.");
                    String num_paquete;
                    do{
                        num_paquete = JOptionPane.showInputDialog("Ingresa el numero de paquete.");
                    }while(num_paquete.isEmpty());
                    Integer num_paquete_int = Integer.parseInt(num_paquete);
                    String document_id;
                    do{
                        document_id = JOptionPane.showInputDialog("Ingresa un documento valido para hacer la reserva");
                    }while(!buscarPersonaId(document_id));
                    String number_participants;
                    do{
                        number_participants = JOptionPane.showInputDialog("Ingresa el numero total de asistentes.");
                    }while(number_participants.isEmpty());
                    int number_participants_int = Integer.parseInt(number_participants);
                    // Obtener la fecha y hora actual
                    LocalDateTime now = LocalDateTime.now();
                    // Formatear la fecha y hora según tus necesidades
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    // Almacenar la fecha y hora actual en una variable
                    String fechaYHoraActual = formattedDateTime;
                    clientAgency.registrarReserva(document_id, (num_paquete_int-1), fechaYHoraActual, number_participants_int);

                    break;
                case 3:
                    String message_ = "Lista de usuarios registrados:\n";
                    int countTwo = 1;
                    for (Persona persona : clientAgency.listarPersonas()) {
                       message_ += "Usuario " + countTwo + ":\n" +  persona.toString() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, message_);

                    break;
                case 4:

                    JOptionPane.showMessageDialog(null, "Has seleccionado Consultar reservas");
                    String document_person;
                    do{
                        document_person = JOptionPane.showInputDialog("Ingresa un documento valido para consultar la reserva");
                    }while(!buscarPersonaId(document_person));
                    Reserva reserva= buscarReserva(document_person);
                    assert reserva != null;
                    TouristPackage touristPackage = buscarPaqueteId(reserva.getTouristPackageId());

                    assert touristPackage != null;
                    JOptionPane.showMessageDialog(null, reserva.toString() + "\n" + touristPackage.toString() );
                    break;
                case 5:
                    //log.info("Has seleccionado salir");
                    active = false;
                    // Lógica para la opción Salir
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    private static Boolean buscarPersonaId (String id){
        for (Persona persona : clientAgency.listarPersonas()) {
            if (persona.getDocumentNumber().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private static Reserva buscarReserva (String id){
        for (Reserva reserva : clientAgency.listarReservas()) {
            if (reserva.getIdPersona().equals(id)) {
                if(buscarPaqueteId(reserva.getTouristPackageId()) != null){
                    return reserva;
                }
            }
        }
        return null;
    }

    private static TouristPackage buscarPaqueteId (Integer id){
        for (TouristPackage touristPackage : clientAgency.listarPaquetes()) {
            if (touristPackage.getId().equals(id)) {
                return touristPackage;
            }
        }
        return null;
    }
}