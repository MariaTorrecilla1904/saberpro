package com.uts.saberpro.repository;

import com.uts.saberpro.model.Resultado;
import com.uts.saberpro.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    // Cuando ya tienes el objeto estudiante
    List<Resultado> findByEstudiante(Estudiante estudiante);

    // Cuando solo tienes el ID, más rápido y limpio
    List<Resultado> findByEstudianteId(Long estudianteId);
}
