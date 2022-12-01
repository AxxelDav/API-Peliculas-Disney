package com.disney.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
//@Builder
@Table(name = "PELICULA")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IMAGEN")
    private String imagen;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "CALIFICACION")
    private Integer calificacion;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Genero genero;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "pelicula_personaje"
            , joinColumns = {@JoinColumn(name = "pelicula_id")}
            , inverseJoinColumns = {@JoinColumn(name = "personaje_id")}
    )
    private List<Personaje> personajes;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }

}