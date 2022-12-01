package com.disney.controlador;

import com.disney.dto.DetallePeliculaDto;
import com.disney.dto.GeneroDto;
import com.disney.dto.PeliculaDtoBusqueda;
import com.disney.entidad.Genero;
import com.disney.entidad.Pelicula;
import com.disney.servicio.impl.ServicioPeliculaImpl;
import com.disney.util.ConversorEntidadAdto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
public class PeliculaControlador {

    @Autowired
    private ServicioPeliculaImpl servicioPeliculaImpl;

    @Autowired
    private ConversorEntidadAdto conversor;


    @ApiOperation(value = "Devuelve una Pelicula basado en su Id", notes = "Este metodo devuelve una Pelicula basado en su Id")
    @GetMapping("/detalle/pelicula/{id}")
    public ResponseEntity<DetallePeliculaDto> detallePelicula(@PathVariable Long id) {
        Pelicula pelicula = servicioPeliculaImpl.detallePelicula(id);
        return new ResponseEntity<>(conversor.convertirPeliculaDtoAdetalle(pelicula), HttpStatus.OK);
    }

    @ApiOperation(value = "Crea una pelicula", notes = "Este metodo crea una nueva Pelicula")
    @PostMapping("/crear/pelicula")
    public ResponseEntity<DetallePeliculaDto> crearPelicula(@RequestBody Pelicula pelicula) {
        Pelicula peliculaCreada = servicioPeliculaImpl.crearPelicula(pelicula);
        return new ResponseEntity<>(conversor.convertirPeliculaDtoAdetalle(peliculaCreada), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edita una Pelicula", notes = "Este metodo permite editar una Pelicula")
    @PutMapping("/editar/pelicula")
    public ResponseEntity<DetallePeliculaDto> editarPelicula(@RequestBody Pelicula pelicula) {
        Pelicula peliculaEditada = servicioPeliculaImpl.editarPelicula(pelicula);
        return new ResponseEntity<>(conversor.convertirPeliculaDtoAdetalle(peliculaEditada), HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina una Pelicula basado en su Id", notes = "Este metodo permite eliminar una Pelicula basado en su Id")
    @DeleteMapping("/eliminar/pelicula/{id}")
    public ResponseEntity<Boolean> eliminarPelicula(@PathVariable Long id) {
        return new ResponseEntity<>(servicioPeliculaImpl.eliminarPelicula(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra las Peliculas por nombre y genero. Y ademas las ordena", notes = "Este metodo permite filtrar Peliculas por Nombre, Genero y ademas ordenar de forma Ascendente o Descendente (dependiendo lo que se le pasa como parametro)")
    @GetMapping("/movies")
    public ResponseEntity<List<PeliculaDtoBusqueda>> filtrarPeliculas(@RequestParam(required = false) String name,
                                                                      @RequestParam(required = false) Long genre,
                                                                      @RequestParam(required = false) String order) {
        List<Pelicula> peliculas = servicioPeliculaImpl.filtrarPeliculas(name, genre, order);
        return new ResponseEntity<>(conversor.convertirPeliculaAdtoBusqueda(peliculas), HttpStatus.OK);
    }

    @ApiOperation(value = "Agrega un Personaje a una Pelicula", notes = "Este metodo permite agregar un Personaje a una Pelicula, cada uno basado en sus Id")
    @PostMapping("/movies/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<DetallePeliculaDto> agregarPersonajeApelicula(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        Pelicula pelicula = servicioPeliculaImpl.agregarPersonajeApelicula(idMovie, idCharacter);
        return new ResponseEntity<>(conversor.convertirPeliculaDtoAdetalle(pelicula), HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina un Personaje de una Pelicula", notes = "Este metodo permite eliminar un Personaje de una Pelicula, cada uno basado en sus Id")
    @DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Boolean> RemoverPersonajeDePelicula(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        return new ResponseEntity<>(servicioPeliculaImpl.removerPersonajeDePelicula(idMovie, idCharacter), HttpStatus.OK);
    }

//    @ApiOperation(value = "Elimina un Personaje de una Pelicula", notes = "Este metodo permite eliminar un Personaje de una Pelicula, cada uno basado en sus Id")
//    @DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
//    public ResponseEntity<String> RemoverPersonajeDePelicula(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
//        servicioPeliculaImpl.removerPersonajeDePelicula(idMovie, idCharacter)
//        return new ResponseEntity<String>("Pelicula eliminada exitosamente", HttpStatus.OK);
//    }
}
