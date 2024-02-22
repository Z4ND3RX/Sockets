package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tipo;
    private Object contenido;

}
