package com.disney.repositorio;

import com.disney.entidad.Pelicula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PeliculaRepositorioTest {

    @Autowired
    private RepositorioPelicula repositorioPelicula;

    @DisplayName("Probamos el metodo save del Repositorio de Pelicula")
    @Test
    public void testSavePeliculaRepository() {
        Pelicula pelicula = new Pelicula();
        Pelicula peliculaGuardada = repositorioPelicula.save(pelicula);
        Assertions.assertNotNull(peliculaGuardada);
    }

    @DisplayName("Probamos el metodo findById del Repositorio de Pelicula")
    @Test
    public void testFindByIdPeliculaRepository() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Dragon Ball Super");
        repositorioPelicula.save(pelicula);
        Optional<Pelicula> peliculaEncontrada = repositorioPelicula.findById(1L);
        Assertions.assertNotNull(peliculaEncontrada);
        Assertions.assertTrue(peliculaEncontrada.isPresent());
        Assertions.assertEquals("Dragon Ball Super", peliculaEncontrada.get().getTitulo());
    }

    @DisplayName("Probamos el metodo deleteById del Repositorio de Pelicula")
    @Test
    public void testDeleteByIdPeliculaRepository() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);

        repositorioPelicula.save(pelicula);
        repositorioPelicula.deleteById(1L);
        Assertions.assertEquals(Optional.empty(), repositorioPelicula.findById(1L));
    }


    @DisplayName("Probamos el metodo findAll del Repositorio de Pelicula")
    @Test
    public void testFindAllPeliculaRepository() {
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
        repositorioPelicula.save(pelicula1);
        repositorioPelicula.save(pelicula2);
        List<Pelicula> peliculasEncontradas = repositorioPelicula.findAll();
        Assertions.assertEquals(peliculasEncontradas.get(0).getId(), peliculas.get(0).getId());
    }
}
