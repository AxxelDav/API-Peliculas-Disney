package com.disney.servicio;

import com.disney.entidad.Pelicula;

import java.util.List;

public interface ServicioPelicula {

    public Pelicula detallePelicula(Long id);

    public Pelicula crearPelicula(Pelicula pelicula);

    public Pelicula editarPelicula(Pelicula pelicula);

    public Boolean eliminarPelicula(Long id);

    public List<Pelicula> filtrarPeliculas(String name, Long genre, String order);

    public Pelicula agregarPersonajeApelicula(Long idMovie, Long idCharacter);

    public Boolean removerPersonajeDePelicula(Long idMovie, Long idCharacter);


}
