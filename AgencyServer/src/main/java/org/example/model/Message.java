package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tipo;
    private Object contenido;

    // Nuevos tipos de mensaje
    public static final String LISTAR_DESTINOS = "listarDestinos";
    public static final String AGREGAR_RESERVA = "agregarReserva";

    public String getTipo() {
        return this.tipo;
    }

    public Object getContenido() {
        return contenido;
    }
}
