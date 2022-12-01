package com.disney.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class DetallePeliculaDto {

    private Long idPelicula;
    private String imagen;
    private String titulo;
    private LocalDateTime fechaCreacion;
    private Integer calificacion;

    GeneroDto genero;

    List<PersonajesDePelicula> personajes;

}
