package com.disney.util;

import com.disney.dto.*;
import com.disney.entidad.Genero;
import com.disney.entidad.Pelicula;
import com.disney.entidad.Personaje;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ConversorEntidadAdto {

    @Autowired
    private ModelMapper modelMapper;


//*******Pelicula******//

    public PeliculaDto convertirPeliculaAdto(Pelicula pelicula) {
        return modelMapper.map(pelicula, PeliculaDto.class);
    }

    public List<PeliculaDto> convertirPeliculaAdto(List<Pelicula> peliculas) {
        return peliculas
                .stream()
                .map(pelicula -> convertirPeliculaAdto(pelicula))
                .collect(Collectors.toList());
    }


    public DetallePeliculaDto convertirPeliculaDtoAdetalle(Pelicula pelicula) {
        return modelMapper.map(pelicula, DetallePeliculaDto.class);
    }


    public PeliculaDtoBusqueda convertirPeliculaAdtoBusqueda(Pelicula pelicula) {
        return modelMapper.map(pelicula, PeliculaDtoBusqueda.class);
    }

    public List<PeliculaDtoBusqueda> convertirPeliculaAdtoBusqueda(List<Pelicula> peliculas) {
        return peliculas
                .stream()
                .map(pelicula -> convertirPeliculaAdtoBusqueda(pelicula))
                .collect(Collectors.toList());
    }



//*******Personaje******//

    public PersonajeDto convertirPersonajeAdto(Personaje personaje) {
        return modelMapper.map(personaje, PersonajeDto.class);
    }

    public List<PersonajeDto> convertirPersonajeAdto(List<Personaje> personajes) {
        return personajes
                .stream()
                .map(personaje -> convertirPersonajeAdto(personaje))
                .collect(Collectors.toList());
    }

    public PersonajeDtoBusqueda convertirPersonajeAdtoBusqueda(Personaje personaje) {
        return modelMapper.map(personaje, PersonajeDtoBusqueda.class);
    }

    public List<PersonajeDtoBusqueda> convertirPersonajeAdtoBusqueda(List<Personaje> personajes) {
        return personajes
                .stream()
                .map(personaje -> convertirPersonajeAdtoBusqueda(personaje))
                .collect(Collectors.toList());
    }




//*******Genero******//

    public GeneroDto convertirGeneroaAdto(Genero genero) {
        return modelMapper.map(genero, GeneroDto.class);
    }

    public List<GeneroDto> convertirGeneroaAdto(List<Genero> generos) {
        return generos
                .stream()
                .map(genero -> convertirGeneroaAdto(genero))
                .collect(Collectors.toList());
    }

}
