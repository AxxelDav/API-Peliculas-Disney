package com.disney.servicio;

import com.disney.entidad.Genero;
import com.disney.entidad.Pelicula;
import com.disney.entidad.Personaje;
import com.disney.exception.PeliculaNoEncontrada;
import com.disney.repositorio.RepositorioPelicula;
import com.disney.repositorio.RepositorioPersonaje;
import com.disney.servicio.impl.ServicioPeliculaImpl;
import com.disney.util.EnumsMensajesExcepcion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ServicioPeliculaImplTest {

    @InjectMocks
    private ServicioPeliculaImpl servicioPeliculaImpl;

    @Mock
    private RepositorioPelicula repositorioPelicula;

    @Mock
    private RepositorioPersonaje repositorioPersonaje;

    @Mock
    Pelicula pelicula;

    @Mock
    Personaje personaje;

    //Pasar el Genero en otra clase Test!! o fijate como podes hacer!!!
//    @InjectMocks
//    private ServicioGenero servicioGenero;
//    private Pelicula pelicula;

    @BeforeEach
    public void setUp() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Dragon Ball");


        Personaje personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Freezer");
    }


    @DisplayName("Probamos el metodo detallePelicula de ServicioPelicula, en el caso de que NO exista la Pelicula buscada")
    @Test
    public void lanzaPeliculaNoEncontradaCuandoNoexisteLaPelicula() {

        Mockito.when(repositorioPelicula.findById(1L)).thenReturn(Optional.of(pelicula));
        PeliculaNoEncontrada peliculaNoEncontrada = Assertions.assertThrows(PeliculaNoEncontrada.class, () -> servicioPeliculaImpl.detallePelicula(3L));
        Assertions.assertEquals(EnumsMensajesExcepcion.PELICULA_NO_ENCONTRADA.getMensaje(), peliculaNoEncontrada.getMessage());
    }

    @DisplayName("Probamos el metodo crearPelicula del servicio")
    @Test
    public void repositorioGuardaCorrectamente() {
        Mockito.when(repositorioPelicula.save(any(Pelicula.class))).thenReturn(pelicula);
        Assertions.assertNotNull(servicioPeliculaImpl.crearPelicula(new Pelicula()));
    }

    @Test
    public void peliculaNoEncontradaAlEditarPelicula() {
        Pelicula peliculaResultado = new Pelicula();
        peliculaResultado.setId(5L);
        Mockito.when(repositorioPelicula.findById(1L)).thenReturn(Optional.of(pelicula));
        Assertions.assertThrows(PeliculaNoEncontrada.class, () -> servicioPeliculaImpl.editarPelicula(peliculaResultado));
    }

    @Test
    public void peliculaEncontradaAlEditarPelicula() {
        Genero genero = new Genero();
        genero.setId(1L);
        genero.setNombre("Batalla");

        Personaje personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Goku");

        List<Personaje> personajes = new ArrayList<>();
        personajes.add(personaje);

        Pelicula peliculaResultado = new Pelicula();

        Mockito.when(repositorioPelicula.findById(any())).thenReturn(Optional.of(peliculaResultado));

        peliculaResultado.setId(1L);
        peliculaResultado.setImagen("Dragon Ball");
        peliculaResultado.setTitulo("Super Heroes");
        peliculaResultado.setCalificacion(10);
        peliculaResultado.setGenero(genero);
        peliculaResultado.setPersonajes(personajes);

        Mockito.when(repositorioPelicula.save(any(Pelicula.class))).thenReturn(peliculaResultado);

        Assertions.assertNotNull(servicioPeliculaImpl.editarPelicula(new Pelicula()));

    }

    @Test
    public void personajesDePeliculaInexistentesYBuscarPorIdFalso() {

        Pelicula pelicula = new Pelicula();
        Assertions.assertNull(pelicula.getPersonajes());
    }

    @Test
    public void falseBuscarPorId() {
        Pelicula pelicula1 = new Pelicula();
        List<Personaje> personajes1 = new ArrayList<>();
        Personaje personaje1 = new Personaje();
        personaje1.setId(1L);
        personaje1.setNombre("Goku");
        personajes1.add(personaje1);
        pelicula1.setPersonajes(personajes1);

        Pelicula pelicula2 = new Pelicula();
        List<Personaje> personajes2 = new ArrayList<>();
        Personaje personaje2 = new Personaje();
        personaje2.setId(2L);
        personaje2.setNombre("Vegueta");
        personajes2.add(personaje2);
        pelicula2.setPersonajes(personajes2);

        Assertions.assertFalse(ServicioPeliculaImpl.buscarPorId(pelicula1, pelicula2));
    }

    @Test
    public void trueBuscarPorId() {
        Pelicula pelicula1 = new Pelicula();
        List<Personaje> personajes1 = new ArrayList<>();
        Personaje personaje1 = new Personaje();
        personaje1.setId(1L);
        personaje1.setNombre("Goku");
        personajes1.add(personaje1);
        pelicula1.setPersonajes(personajes1);

        Pelicula pelicula2 = new Pelicula();
        List<Personaje> personajes2 = new ArrayList<>();
        Personaje personaje2 = new Personaje();
        personaje2.setId(1L);
        personaje2.setNombre("Vegueta");
        personajes2.add(personaje2);
        pelicula2.setPersonajes(personajes2);

        Assertions.assertTrue(ServicioPeliculaImpl.buscarPorId(pelicula1, pelicula2));
    }


    @Test
    public void lanzaExceptionAlEliminarPelicula() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Dragon Ball Super");

        Mockito.when(repositorioPelicula.findById(1L)).thenReturn(Optional.of(pelicula));

        Assertions.assertThrows(PeliculaNoEncontrada.class, () -> servicioPeliculaImpl.eliminarPelicula(3L));
    }

    @Test
    public void trueAlEliminarPelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);

        Mockito.when(repositorioPelicula.findById(1L)).thenReturn(Optional.of(pelicula));
        Assertions.assertTrue(servicioPeliculaImpl.eliminarPelicula(pelicula.getId()));
        Mockito.verify(repositorioPelicula).deleteById(1L);
    }


    @Test
    public void testAgregarPersonaApelicula() {
        Mockito.when(repositorioPelicula.findById(1L)).thenReturn(Optional.ofNullable(pelicula));
        Mockito.when(repositorioPersonaje.findById(1L)).thenReturn(Optional.ofNullable(personaje));
        Mockito.when(repositorioPelicula.save(any(Pelicula.class))).thenReturn(pelicula);
        pelicula.getPersonajes().add(personaje);
        Assertions.assertNotNull(servicioPeliculaImpl.agregarPersonajeApelicula(1L, 1L));
    }

    @Test
    public void falseRemoverPersonajeDePelicula() {

        Long idMovie = 1L;
        Long idCharacter = 1L;

        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Dragon Ball");

        Personaje personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Freezer");

        Personaje personaje2 = new Personaje();
        personaje2.setId(2L);
        personaje2.setNombre("Cooler");

        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(pelicula);

        List<Personaje> personajes = new ArrayList<>();
        personajes.add(personaje);
        personajes.add(personaje2);

        pelicula.setPersonajes(personajes);

        Mockito.when(repositorioPelicula.findAll()).thenReturn(peliculas);
        Mockito.when(repositorioPelicula.save(pelicula)).thenReturn(pelicula);

        Assertions.assertTrue(pelicula.getPersonajes().remove(personaje));
        Assertions.assertFalse(servicioPeliculaImpl.removerPersonajeDePelicula(idMovie, idCharacter));
    }


//    @Test
//    public void lanzaGeneroIngresadoIncorrecto() {
////        servicioGenero = new ServicioGenero();
//        Genero genero = new Genero();
//        genero.setNombre(null);
//        genero.setImagen(null);
//
//        GeneroIngresadoIncorrecto generoIngresadoIncorrecto = Assertions.assertThrows(GeneroIngresadoIncorrecto.class,
//                () -> servicioGenero.crearGenero(genero));
//
//        Assertions.assertEquals(EnumsMensajesExcepcion.GENERO_INGRESADO_INCORRECTO.getMensaje(), generoIngresadoIncorrecto.getMessage());
//
//    }


}
