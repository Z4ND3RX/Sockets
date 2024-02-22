package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
public class TouristPackage implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date date;
    private Integer time;
    private Double price;
    private ArrayList<String> destinations;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(name).append("\n");
        sb.append("Fecha: ").append(date.toString()).append("\n");
        sb.append("Duración: ").append(time).append(" días\n");
        sb.append("Precio: $").append(price).append("\n");
        sb.append("Destinos: ").append(destinations.toString()).append("\n");
        return sb.toString();
    }


}
