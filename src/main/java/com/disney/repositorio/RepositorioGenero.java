package com.disney.repositorio;

import com.disney.entidad.Genero;
import com.disney.entidad.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioGenero extends JpaRepository<Genero, Long> {

    public List<Genero> findAllByOrderByImagenAsc(); //funcionaaa es del nuevo metodo controlador que agregueeee para probarr

}
