package com.disney.repositorio;

import com.disney.entidad.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioPelicula extends JpaRepository<Pelicula, Long> {

//    public List<Pelicula> findAllByOrderByfechaCreacionAsc(); //Ejemplo para metodos nativos de JPA

    //Estas Querys funcionan con el otro metodo "filtrarPeliculas" del ServicioPeliculaImpl

//    @Query(value = "select * from Pelicula p join Genero g where (p.titulo = :titulo) or (g.id= :genero) order by FECHA_CREACION ASC", nativeQuery = true) //Otra opcion, pero tengo que averiguar porque obtengo mas registros de los que necesito
//    @Query(value = "select * from Pelicula p where (p.titulo = :titulo) or (p.genero_id= :genero) order by FECHA_CREACION ASC", nativeQuery = true) //genero_id lo saqué de la tabla de la BD. Podria haberle puesto el nombre a la entidad con @Column (pero eso tiene que ser antes de que las tablas esten creadas, porque o sino hay cconflicto!!)
//    public List<Pelicula> peliculasPorQuerysASC(@Param("titulo") String titulo, @Param("genero") Long genero);
//
//    @Query(value = "select * from Pelicula p join Genero g where (p.titulo = :titulo) or (g.id= :genero) order by FECHA_CREACION DESC", nativeQuery = true)
//    public List<Pelicula> peliculasPorQuerysDESC(@Param("titulo") String titulo, @Param("genero") Long genero);

}
