package com.uts.saberpro.service;

import com.uts.saberpro.model.Estudiante;
import com.uts.saberpro.model.Resultado;
import com.uts.saberpro.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultadoService {

    private final ResultadoRepository repo;

    public ResultadoService(ResultadoRepository repo) {
        this.repo = repo;
    }

    public List<Resultado> listar() {
        return repo.findAll();
    }

    public List<Resultado> listarPorEstudiante(Estudiante est) {
        return repo.findByEstudiante(est);
    }

    public Resultado guardar(Resultado r) {
        return repo.save(r);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public Optional<Resultado> buscar(Long id) {
        return repo.findById(id);
    }

    // PROMEDIO por estudiante
    public double promedioEstudiante(Estudiante est) {
        List<Resultado> lista = repo.findByEstudiante(est);
        if (lista.isEmpty()) return 0.0;
        return lista.stream()
                .collect(Collectors.averagingDouble(Resultado::getPuntaje));
    }
}
