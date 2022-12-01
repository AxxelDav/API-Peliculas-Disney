package com.disney.servicio;

import com.disney.entidad.Genero;
import com.disney.exception.GeneroIngresadoIncorrecto;
import com.disney.repositorio.RepositorioGenero;
import com.disney.servicio.impl.ServicioGeneroImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ServicioGeneroImplTest {

    @InjectMocks
    private ServicioGeneroImpl servicioGeneroImpl;

    @Mock
    private RepositorioGenero repositorioGenero;

    @BeforeEach
    public void setUp() {
    }

    //Este no va por el HappyPath
    @DisplayName("Lanza una excepcion cuando el Genero NO es valido, osea su imagen || su Nombre es null")
    @Test
    public void lanzaExepcionGeneroIncorrecto() {
        Genero genero2 = new Genero();
        genero2.setNombre(null);
        genero2.setImagen(null);
        Assertions.assertThrows(GeneroIngresadoIncorrecto.class, () -> servicioGeneroImpl.crearGenero(genero2));
    }


    @DisplayName("Test para creacion de un Genero correcto con imagen y nombre")
    @Test
    public void crearGeneroCorrecto() {
        Genero genero = new Genero();
        genero.setNombre("Anime");
        genero.setImagen("Super Heroes");
        Mockito.when(repositorioGenero.save(any(Genero.class))).thenReturn(genero);
        Assertions.assertNotNull(servicioGeneroImpl.crearGenero(genero));

    }

}
