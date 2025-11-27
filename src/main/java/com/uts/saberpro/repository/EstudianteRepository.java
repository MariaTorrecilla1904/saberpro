package com.uts.saberpro.repository;

import com.uts.saberpro.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByCodigo(String codigo);
}
