package com.disney.servicio;

import com.disney.entidad.Personaje;
import com.disney.exception.PersonajeNoEncontrado;
import com.disney.repositorio.RepositorioPersonaje;
import com.disney.servicio.impl.ServicioPersonajeImpl;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ServicioPersonajeImplTest {

    @InjectMocks
    private ServicioPersonajeImpl servicioPersonajeImpl;

    @Mock
    private RepositorioPersonaje repositorioPersonaje;

    @Mock
    Personaje personaje;

    @BeforeEach
    public void setUp() {
        Personaje personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Goku");
    }

    @DisplayName("Este es el test del metodo findPersonajeById, en caso de que NO se encuentre Personaje lanza una excepcion")
    @Test
    public void lanzaExcepcionAlNoEncontrarPersonajePorId() {

        Mockito.when(repositorioPersonaje.findById(1L)).thenReturn(Optional.of(personaje));
        PersonajeNoEncontrado personajeNoEncontrado = Assertions.assertThrows(PersonajeNoEncontrado.class, () -> servicioPersonajeImpl.findPersonajeById(3L));
        Assertions.assertEquals(EnumsMensajesExcepcion.PERSONAJE_NO_ENCONTRADO.getMensaje(), personajeNoEncontrado.getMessage());
    }

    @DisplayName("Este es el test del metodo crearPersonaje")
    @Test
    public void testCrearPersonaje() {
        Mockito.when(repositorioPersonaje.save(any(Personaje.class))).thenReturn(personaje);
        Assertions.assertNotNull(servicioPersonajeImpl.crearPersonaje(new Personaje()));
    }

    @DisplayName("Lanza una Excepcion si no encuentra Personaje para editar.")
    @Test
    public void lanzaExcepcionAlNoEncontrarPersonajeParaEditar() {
        Personaje personaje2 = new Personaje();
        Mockito.when(repositorioPersonaje.findById(1L)).thenReturn(Optional.of(personaje));
        Assertions.assertThrows(PersonajeNoEncontrado.class, () -> servicioPersonajeImpl.editarPersonaje(personaje2));
    }

    @DisplayName("Test para el metodo editarPersonaje")
    @Test
    public void testEditarPersonajeEncontrado() {
        Personaje personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Goku");
        personaje.setPeso(70);
        personaje.setImagen("Super Heroe");
        personaje.setHistoria("Guerrero Saiyajin");
        personaje.setEdad(40);

        Mockito.when(repositorioPersonaje.findById(1L)).thenReturn(Optional.of(personaje));
        Mockito.when(repositorioPersonaje.save(any(Personaje.class))).thenReturn(personaje);

        Assertions.assertNotNull(servicioPersonajeImpl.editarPersonaje(personaje));
    }

    @DisplayName("Test para el metodo eliminarPersonaje")
    @Test
    public void testEliminarPelicula() {
        Personaje personaje = new Personaje();
        personaje.setId(1L);

        Mockito.when(repositorioPersonaje.findById(1L)).thenReturn(Optional.of(personaje));
        Assertions.assertTrue(servicioPersonajeImpl.eliminarPersonaje(personaje.getId()));
        Mockito.verify(repositorioPersonaje).deleteById(1L);
    }

}
