package com.disney.entidad;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@Builder
@Table(name = "GENERO")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "IMAGEN")
    private String imagen;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Pelicula> peliculas;


}
