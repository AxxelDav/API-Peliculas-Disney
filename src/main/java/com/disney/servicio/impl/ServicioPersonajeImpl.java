package com.disney.servicio.impl;

import com.disney.entidad.Pelicula;
import com.disney.entidad.Personaje;
import com.disney.exception.PersonajeNoEncontrado;
import com.disney.repositorio.RepositorioPelicula;
import com.disney.repositorio.RepositorioPersonaje;
import com.disney.servicio.ServicioPersonaje;
import com.disney.util.EnumsMensajesExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioPersonajeImpl implements ServicioPersonaje {

    @Autowired
    private RepositorioPersonaje repositorioPersonaje;

    @Autowired
    private RepositorioPelicula repositorioPelicula;

    public Personaje findPersonajeById(Long id) {
        return repositorioPersonaje.findById(id)//.get();
                                   .orElseThrow(() -> new PersonajeNoEncontrado(EnumsMensajesExcepcion.PERSONAJE_NO_ENCONTRADO.getMensaje()));
    }

    public Personaje crearPersonaje(Personaje personaje) {
        return repositorioPersonaje.save(personaje);
    }

    public Personaje editarPersonaje(Personaje personaje) throws PersonajeNoEncontrado {
        Personaje personajeAeditar = repositorioPersonaje.findById(personaje.getId())//.get();
                                                          .orElseThrow(() -> new PersonajeNoEncontrado(EnumsMensajesExcepcion.PERSONAJE_NO_ENCONTRADO.getMensaje()));
        personajeAeditar.setNombre(personaje.getNombre());
        personajeAeditar.setPeso(personaje.getPeso());
        personajeAeditar.setImagen(personaje.getImagen());
        personajeAeditar.setHistoria(personaje.getHistoria());
        personajeAeditar.setEdad(personaje.getEdad());
        return repositorioPersonaje.save(personajeAeditar);
    }

    public Boolean eliminarPersonaje(Long id) throws PersonajeNoEncontrado{
        Personaje personaje = repositorioPersonaje.findById(id).orElseThrow(() -> new PersonajeNoEncontrado(EnumsMensajesExcepcion.PERSONAJE_NO_ENCONTRADO.getMensaje()));
        repositorioPersonaje.deleteById(personaje.getId());
        return true;
    }


    public List<Personaje> filtrarPersonajes(String name, Integer age, Long movies) {
        List<Personaje> personajes = repositorioPersonaje.findAll();
        List<Personaje> personajesResultado = new ArrayList<>();

        if (name == null && age == null && movies == null)
            return personajes;

        for (Personaje personaje : personajes) {
            if (personaje.getNombre().equals(name) || Objects.equals(personaje.getEdad(), age))
                personajesResultado.add(personaje);
            else {
                for (Pelicula pelicula : personaje.getPeliculas()) {
                    if(Objects.equals(pelicula.getId(), movies))
                        personajesResultado.add(personaje);
                }
            }
        }
        return personajesResultado;
    }
}
