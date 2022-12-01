package com.disney.servicio.impl;

import com.disney.dto.GeneroDto;
import com.disney.entidad.Genero;
import com.disney.repositorio.RepositorioGenero;
import com.disney.servicio.ServicioGenero;
import com.disney.util.ConversorEntidadAdto;
import com.disney.util.ValidaGenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioGeneroImpl implements ServicioGenero {

    @Autowired
    private RepositorioGenero repositorioGenero;

    public Genero crearGenero(Genero genero) {
        ValidaGenero.validaGenero(genero);
        return repositorioGenero.save(genero);
    }

}
