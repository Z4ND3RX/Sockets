package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String email;
    private String documentNumber;
    private String address;
    private String phoneNumber;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(name).append("\n");
        sb.append("email: ").append(email).append("\n");
        sb.append("No. Documento: ").append(documentNumber).append("\n");
        sb.append("Dirección: $").append(address).append("\n");
        sb.append("Número de teléfono: ").append(phoneNumber).append("\n");
        return sb.toString();
    }

}
