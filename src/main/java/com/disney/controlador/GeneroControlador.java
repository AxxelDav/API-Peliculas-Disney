package com.disney.controlador;

import com.disney.dto.GeneroDto;
import com.disney.entidad.Genero;
import com.disney.servicio.impl.ServicioGeneroImpl;
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
public class GeneroControlador {

    @Autowired
    private ServicioGeneroImpl servicioGeneroImpl;

    @Autowired
    private ConversorEntidadAdto conversor;

    @ApiOperation(value = "Crea un nuevo genero", notes = "Esta Operacion permite crear un nuevo genero")
    @PostMapping("/crear/genero")
    public ResponseEntity<GeneroDto> crearGenero(@RequestBody Genero genero) {
        Genero generoFinal = servicioGeneroImpl.crearGenero(genero);
        return new ResponseEntity<>(conversor.convertirGeneroaAdto(generoFinal), HttpStatus.CREATED);
    }
}
