package com.disney.servicio;

import com.disney.entidad.Personaje;

import java.util.List;

public interface ServicioPersonaje {

    public Personaje findPersonajeById(Long id);

    public Personaje crearPersonaje(Personaje personaje);

    public Personaje editarPersonaje(Personaje personaje);

    public Boolean eliminarPersonaje(Long id);

    public List<Personaje> filtrarPersonajes(String name, Integer age, Long movies);

}
