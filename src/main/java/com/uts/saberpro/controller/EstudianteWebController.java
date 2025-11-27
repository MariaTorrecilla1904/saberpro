package com.uts.saberpro.controller;

import com.uts.saberpro.model.Estudiante;
import com.uts.saberpro.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/estudiantes")
public class EstudianteWebController {

    private final EstudianteService service;

    public EstudianteWebController(EstudianteService service) {
        this.service = service;
    }

    // LISTAR ESTUDIANTES
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", service.listar());
        return "estudiantes-list"; // templates/estudiantes-list.html
    }

    // FORMULARIO NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes-form"; // templates/estudiantes-form.html
    }

    // GUARDAR (CREAR / EDITAR)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        service.guardar(estudiante);
        return "redirect:/web/estudiantes";
    }

    // FORMULARIO EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Estudiante est = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        model.addAttribute("estudiante", est);
        return "estudiantes-form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        if (service.existe(id)) {
            service.eliminar(id);
        }
        return "redirect:/web/estudiantes";
    }
}
