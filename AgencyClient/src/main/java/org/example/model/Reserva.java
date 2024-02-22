package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@ToString
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String idPersona;
    private Integer touristPackageId;
    private String date;
    private Integer amountPeople;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(idPersona).append("\n");
        sb.append("fecha: ").append(date).append("\n");
        sb.append("cantidad de personas: ").append(amountPeople).append("\n");
        return sb.toString();
    }

}
