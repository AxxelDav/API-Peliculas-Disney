package com.disney.controlador;


import com.disney.dto.PersonajeDto;
import com.disney.dto.PersonajeDtoBusqueda;
import com.disney.entidad.Personaje;
import com.disney.servicio.impl.ServicioPersonajeImpl;
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
public class PersonajeControlador {

    @Autowired
    private ServicioPersonajeImpl servicioPersonajeImpl;

    @Autowired
    private ConversorEntidadAdto conversor;

    @ApiOperation(value = "Regresa detalle de Personaje basado en su Id", notes = "Este metodo obtener los detalles de un Personaje basado en su Id")
    @GetMapping("/detalle/personaje/{id}") //OK
    public ResponseEntity<PersonajeDto> findPersonajeById (@PathVariable Long id) {
        Personaje personaje = servicioPersonajeImpl.findPersonajeById(id);
        return new ResponseEntity<>(conversor.convertirPersonajeAdto(personaje), HttpStatus.OK);
    }

    @ApiOperation(value = "Crea un Personaje", notes = "Este metodo crea un nuevo Personaje")
    @PostMapping("/crear/personaje")
    public ResponseEntity<PersonajeDto> createPersonaje(@RequestBody Personaje personaje) {
        Personaje personaje1 = servicioPersonajeImpl.crearPersonaje(personaje);
        return new ResponseEntity<>(conversor.convertirPersonajeAdto(personaje1), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edita un Personaje", notes = "Este metodo permite editar un Personaje")
    @PutMapping("/editar/personaje") //podria no pasarle un @PathVariable
    public ResponseEntity<PersonajeDto> editarPersonaje(@RequestBody Personaje personaje) {
        Personaje personajeEditado = servicioPersonajeImpl.editarPersonaje(personaje);
        return new ResponseEntity<>(conversor.convertirPersonajeAdto(personaje), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina un Personaje basado en su Id", notes = "Este metodo permite eliminar un Personaje basado en su Id")
    @DeleteMapping("/eliminar/personaje/{id}")
    public ResponseEntity<Boolean> eliminarPersonaje(@PathVariable Long id) {
        return new ResponseEntity<>(servicioPersonajeImpl.eliminarPersonaje(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra Personaje por nombre, genero o  el Id de sus Peliculas", notes = "Este metodo permite filtrar Personajes por nombre, genero o  el Id de las Peliculas en las que participa")
    @GetMapping("/characters")
    public ResponseEntity<List<PersonajeDtoBusqueda>> filtrarPersonajes(@RequestParam(required = false) String name,
                                                                        @RequestParam(required = false) Integer age,
                                                                        @RequestParam(required = false) Long movies) {
        List<Personaje> personajes = servicioPersonajeImpl.filtrarPersonajes(name, age, movies);
        return new ResponseEntity<>(conversor.convertirPersonajeAdtoBusqueda(personajes), HttpStatus.OK);
    }

}
