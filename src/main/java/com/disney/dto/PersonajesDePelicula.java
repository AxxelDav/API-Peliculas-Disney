package com.disney.dto;

import lombok.Data;

@Data
public class PersonajesDePelicula {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private float peso;
    private String historia;
}
