package com.disney.servicio.impl;

import com.disney.entidad.Genero;
import com.disney.entidad.Pelicula;
import com.disney.entidad.Personaje;
import com.disney.exception.PeliculaNoEncontrada;
import com.disney.exception.PersonajeNoEncontrado;
import com.disney.repositorio.RepositorioPelicula;
import com.disney.repositorio.RepositorioPersonaje;
import com.disney.servicio.ServicioPelicula;
import com.disney.util.CompararPeliculas;
import com.disney.util.EnumsMensajesExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioPeliculaImpl implements ServicioPelicula {

    @Autowired
    private RepositorioPelicula repositorioPelicula;

    @Autowired
    private RepositorioPersonaje repositorioPersonaje;


    public Pelicula detallePelicula(Long id) throws PeliculaNoEncontrada {
        return repositorioPelicula.findById(id)//.get();
                                  .orElseThrow(() -> new PeliculaNoEncontrada(EnumsMensajesExcepcion.PELICULA_NO_ENCONTRADA.getMensaje()));
    }

    public Pelicula crearPelicula(Pelicula pelicula) {
        return repositorioPelicula.save(pelicula);
    }

    public Pelicula editarPelicula(Pelicula pelicula) throws PeliculaNoEncontrada {
        Pelicula peliculaAmodificar = repositorioPelicula.findById(pelicula.getId())//.get();
                                                          .orElseThrow(() -> new PeliculaNoEncontrada(EnumsMensajesExcepcion.PELICULA_NO_ENCONTRADA.getMensaje()));
        peliculaAmodificar.setImagen(pelicula.getImagen());
        peliculaAmodificar.setTitulo(pelicula.getTitulo());
        peliculaAmodificar.setFechaCreacion(pelicula.getFechaCreacion());
        peliculaAmodificar.setCalificacion(pelicula.getCalificacion());
        peliculaAmodificar.setGenero(pelicula.getGenero());
        if (pelicula.getPersonajes() != null && !ServicioPeliculaImpl.buscarPorId(peliculaAmodificar, pelicula))
            peliculaAmodificar.getPersonajes().addAll(pelicula.getPersonajes());
        return repositorioPelicula.save(peliculaAmodificar);
    }


    public Boolean eliminarPelicula(Long id) throws PeliculaNoEncontrada {
        Pelicula pelicula = repositorioPelicula.findById(id).orElseThrow(() -> new PeliculaNoEncontrada(EnumsMensajesExcepcion.PELICULA_NO_ENCONTRADA.getMensaje()));
        repositorioPelicula.deleteById(pelicula.getId());
        return true;
    }


    public List<Pelicula> filtrarPeliculas(String name, Long genre, String order) {
        List<Pelicula> peliculas = repositorioPelicula.findAll();
        List<Pelicula> peliculasResultado = new ArrayList<>();

        if(name == null && genre == null && order == null)
            return peliculas;

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitulo().equals(name) || Objects.equals(pelicula.getGenero().getId(), genre)) {
                peliculasResultado.add(pelicula);
            }
        }

        if (order != null && order.toUpperCase().equals("ASC")) {
            Collections.sort(peliculas, new CompararPeliculas());
            peliculasResultado.addAll(peliculas);
            return peliculasResultado;
        }

        if (order != null && order.toUpperCase().equals("DESC")) {
            Collections.sort(peliculas, new CompararPeliculas().reversed());
            peliculasResultado.addAll(peliculas);
            return peliculasResultado;
        }

        return peliculasResultado;
    }


    public Pelicula agregarPersonajeApelicula(Long idMovie, Long idCharacter) {
        Pelicula pelicula = repositorioPelicula.findById(idMovie)
                                               .orElseThrow(() -> new PeliculaNoEncontrada(EnumsMensajesExcepcion.PELICULA_NO_ENCONTRADA.getMensaje()));
        Personaje personaje = repositorioPersonaje.findById(idCharacter)
                                                  .orElseThrow(() -> new PersonajeNoEncontrado(EnumsMensajesExcepcion.PERSONAJE_NO_ENCONTRADO.getMensaje()));
        pelicula.getPersonajes().add(personaje);
        return repositorioPelicula.save(pelicula);
    }


    public Boolean removerPersonajeDePelicula(Long idMovie, Long idCharacter) {
        List<Pelicula> peliculas = repositorioPelicula.findAll();
        for (Pelicula pelicula : peliculas) {
            if (Objects.equals(pelicula.getId(), idMovie)) {
                for (Personaje personaje : pelicula.getPersonajes()) {
                    if (Objects.equals(personaje.getId(), idCharacter)) {
                        pelicula.getPersonajes().remove(personaje);
                        repositorioPelicula.save(pelicula);
                        return true;
//            pelicula.getPersonajes().removeIf(personaje -> Objects.equals(personaje.getId(), idCharacter));  ES LO MISMO de la linea 100 a 102
                    }
                }
            }
        }
        return  false;
    }


    //Metodo Auxiliar. Busco por ID
    public static boolean buscarPorId(Pelicula pelicula1, Pelicula pelicula2) {
        for (Personaje personaje: pelicula1.getPersonajes()) {
            for (Personaje personaje1 : pelicula2.getPersonajes()) {
                if (Objects.equals(personaje.getId(), personaje1.getId()))
                    return true;
            }
        }
        return false;
    }



//    public List<Pelicula> filtrarPeliculas(String name, Long genre, String order) {
//        List<Pelicula> peliculas = repositorioPelicula.findAll();
//        if(name == null && genre == null && order == null)
//            return peliculas;
//        if(order.equals("ASC")) return repositorioPelicula.peliculasPorQuerysASC(name, genre);
//        if(order.equals("DESC")) return repositorioPelicula.peliculasPorQuerysDESC(name, genre);
//        else throw new RuntimeException("El order ingresado es Invalido");
//    }

//    public Pelicula editarPelicula(Pelicula pelicula) {
//        Pelicula peliculaAmodificar = repositorioPelicula.findById(pelicula.getId()).get();
//        peliculaAmodificar.setImagen(pelicula.getImagen());
//        peliculaAmodificar.setTitulo(pelicula.getTitulo());
//        peliculaAmodificar.setFechaCreacion(pelicula.getFechaCreacion());
//        peliculaAmodificar.setCalificacion(pelicula.getCalificacion());
//        peliculaAmodificar.setGenero(pelicula.getGenero());
////        peliculaAmodificar.setPersonajes(pelicula.getPersonajes()); //Esto haria que los personajes que tengo en peliculaAmodificar se cambien TODOO por los personajes que tiene pelicula
//        //ME PERMITE AGREGAR PERSONAJES A UNA PELICULA siempre que los personajes de "pelicula" no venga nulo, y que ningun id de personajes de "pelicula" sea el mismo que ningun id de personajes de "peliculaAmodificar"
//        if (pelicula.getPersonajes() != null && !ServicioPelicula.buscarPorId(peliculaAmodificar, pelicula))
//            peliculaAmodificar.getPersonajes().addAll(pelicula.getPersonajes());
//        //En el caso de que haya un match por ID de personaje en ambos, HAGO el seteo de los nuevos atributo/s de personaje/s.
//        //Esto me permite hacer un PUT de personajes DESDE pelicula(no me lo pide el enunciado, pero está bueno saberlo)
//        if(ServicioPelicula.buscarPorId(peliculaAmodificar, pelicula)) {
//            for (Personaje personaje : peliculaAmodificar.getPersonajes()) {
//                for (Personaje personaje1 : pelicula.getPersonajes()) {
//                    if (Objects.equals(personaje.getId(), personaje1.getId())) {
//                        personaje.setImagen(personaje1.getImagen());
//                        personaje.setNombre(personaje1.getNombre());
//                        personaje.setEdad(personaje1.getEdad());
//                        personaje.setPeso(personaje1.getPeso());
//                        personaje.setHistoria(personaje1.getHistoria());
//                    }
//                }
//            }
//        }
//        return repositorioPelicula.save(peliculaAmodificar);
//    }

}
