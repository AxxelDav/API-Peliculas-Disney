package com.disney.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@Builder
@Table(name = "PERSONAJE")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IMAGEN")
    private String imagen;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "PESO")
    private float peso;

    @Column(name = "HISTORIA")
    private String historia;

//    @JsonIgnore
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pelicula> peliculas;

}
