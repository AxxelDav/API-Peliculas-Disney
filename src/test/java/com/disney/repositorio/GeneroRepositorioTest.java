package com.disney.repositorio;

import com.disney.entidad.Genero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class GeneroRepositorioTest {

    @Autowired
    private RepositorioGenero repositorioGenero;

    @DisplayName("Probamos el metodo save del Repositorio de Genero")
    @Test
    public void testSavePeliculaRepository() {
        Genero genero = new Genero();
        Genero generoGuardado = repositorioGenero.save(genero);
        Assertions.assertNotNull(generoGuardado);
    }

}
