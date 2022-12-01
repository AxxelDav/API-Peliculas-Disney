package com.disney.repositorio;

import com.disney.entidad.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPersonaje extends JpaRepository<Personaje, Long> {
}
