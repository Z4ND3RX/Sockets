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

}
