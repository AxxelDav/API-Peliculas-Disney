package com.disney.repositorio;

import com.disney.entidad.Personaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PersonajeRepositorioTest {

    @Autowired
    private RepositorioPersonaje repositorioPersonaje;

    @DisplayName("Probamos el metodo save del Repositorio de Personaje")
    @Test
    public void testSavePeliculaRepository() {
        Personaje personaje = new Personaje();
        Personaje personajeGuardado = repositorioPersonaje.save(personaje);
        Assertions.assertNotNull(personajeGuardado);
    }


    @DisplayName("Probamos el metodo findById del Repositorio de Personaje")
    @Test
    public void testFindByIdPeliculaRepository() {
        Personaje personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Goku");
        repositorioPersonaje.save(personaje);
        Optional<Personaje> personajeEncontrado = repositorioPersonaje.findById(1L);
        Assertions.assertNotNull(personajeEncontrado);
        Assertions.assertTrue(personajeEncontrado.isPresent());
        Assertions.assertEquals("Goku", personajeEncontrado.get().getNombre());
    }


    @DisplayName("Probamos el metodo deleteById del Repositorio de Personaje")
    @Test
    public void testDeleteByIdPeliculaRepository() {
        Personaje personaje = new Personaje();
        personaje.setId(1L);
        repositorioPersonaje.save(personaje);
        repositorioPersonaje.deleteById(1L);
        Assertions.assertEquals(Optional.empty(), repositorioPersonaje.findById(1L));
    }


    @DisplayName("Probamos el metodo findAll del Repositorio de Personaje")
    @Test
    public void testFindAllPeliculaRepository() {
        Personaje personaje1 = new Personaje();
        personaje1.setId(1L);
        Personaje personaje2 = new Personaje();
        personaje2.setId(2L);
        List<Personaje> personajes = Arrays.asList(personaje1, personaje2);
        repositorioPersonaje.save(personaje1);
        repositorioPersonaje.save(personaje2);
        List<Personaje> personajesEncontrados = repositorioPersonaje.findAll();
        Assertions.assertEquals(personajesEncontrados.get(0).getId(), personajes.get(0).getId());
    }

}
