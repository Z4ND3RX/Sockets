package org.example.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

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
}
