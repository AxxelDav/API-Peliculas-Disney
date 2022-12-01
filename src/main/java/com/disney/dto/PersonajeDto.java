package com.disney.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonajeDto {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private float peso;
    private String historia;

    List<PeliculaDto> peliculas;
}
