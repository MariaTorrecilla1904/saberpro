package com.uts.saberpro.controller;

import com.uts.saberpro.model.Estudiante;
import com.uts.saberpro.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // GET /api/estudiantes
    @GetMapping
    public List<Estudiante> listar() {
        return service.listar();
    }

    // GET /api/estudiantes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> verPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/estudiantes
    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante e) {
        Estudiante guardado = service.guardar(e);
        return ResponseEntity.ok(guardado);
    }

    // PUT /api/estudiantes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id,
                                                 @RequestBody Estudiante datos) {
        return service.buscarPorId(id)
                .map(existente -> {
                    existente.setCodigo(datos.getCodigo());
                    existente.setNombre(datos.getNombre());
                    existente.setPrograma(datos.getPrograma());
                    Estudiante actualizado = service.guardar(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/estudiantes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
