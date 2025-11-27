package com.uts.saberpro.service;

import com.uts.saberpro.model.Coordinador;
import com.uts.saberpro.repository.CoordinadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinadorService {

    private final CoordinadorRepository repo;

    public CoordinadorService(CoordinadorRepository repo) {
        this.repo = repo;
    }

    // LISTAR TODOS
    public List<Coordinador> listar() {
        return repo.findAll();
    }

    // BUSCAR POR ID
    public Optional<Coordinador> buscar(Long id) {
        return repo.findById(id);
    }

    // GUARDAR (CREAR / EDITAR)
    public Coordinador guardar(Coordinador c) {
        return repo.save(c);
    }

    // ELIMINAR POR ID
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
