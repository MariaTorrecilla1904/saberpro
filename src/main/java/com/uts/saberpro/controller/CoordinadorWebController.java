package com.uts.saberpro.controller;

import com.uts.saberpro.model.Coordinador;
import com.uts.saberpro.service.CoordinadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/coordinadores")
public class CoordinadorWebController {

    private final CoordinadorService service;

    public CoordinadorWebController(CoordinadorService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("coordinadores", service.listar());
        return "coordinadores-list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("coordinador", new Coordinador());
        return "coordinadores-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Coordinador c) {
        service.guardar(c);
        return "redirect:/web/coordinadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Coordinador c = service.buscar(id)
                .orElseThrow(() -> new RuntimeException("Coordinador no encontrado"));
        model.addAttribute("coordinador", c);
        return "coordinadores-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/web/coordinadores";
    }
}
