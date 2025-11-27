package com.uts.saberpro.service;

import com.uts.saberpro.model.Estudiante;
import com.uts.saberpro.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository repo;

    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }

    public List<Estudiante> listar() {
        return repo.findAll();
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Estudiante guardar(Estudiante e) {
        return repo.save(e);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public boolean existe(Long id) {
        return repo.existsById(id);
    }
}
