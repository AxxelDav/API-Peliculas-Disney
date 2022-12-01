package com.disney.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PeliculaDtoBusqueda {

//    private Long idPelicula;
    private String imagen;
    private String titulo;
    private LocalDateTime fechaCreacion;
}
