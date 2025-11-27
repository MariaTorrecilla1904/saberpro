package com.uts.saberpro.controller;

import com.uts.saberpro.model.Resultado;
import com.uts.saberpro.model.Estudiante;
import com.uts.saberpro.repository.ResultadoRepository;
import com.uts.saberpro.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/web/resultados")
public class ResultadoController {

    private final ResultadoRepository resultadoRepository;
    private final EstudianteRepository estudianteRepository;

    public ResultadoController(ResultadoRepository resultadoRepository,
                               EstudianteRepository estudianteRepository) {
        this.resultadoRepository = resultadoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    // ============================================
    // LISTAR RESULTADOS DE UN ESTUDIANTE
    // ============================================
    @GetMapping("/estudiante/{idEstudiante}")
    public String listarResultadosEstudiante(@PathVariable Long idEstudiante, Model model) {

        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        // CORREGIDO: buscar por estudiante, no por ID
        List<Resultado> resultados = resultadoRepository.findByEstudiante(estudiante);

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("resultados", resultados);

        return "resultado_lista";
    }

    // ============================================
    // FORMULARIO NUEVO RESULTADO
    // ============================================
    @GetMapping("/nuevo/{idEstudiante}")
    public String mostrarFormularioNuevo(@PathVariable Long idEstudiante, Model model) {

        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Resultado resultado = new Resultado();
        resultado.setEstudiante(estudiante); // IMPORTANTE

        model.addAttribute("resultado", resultado);
        model.addAttribute("estudiante", estudiante);

        return "resultado_form";
    }

    // ============================================
    // GUARDAR RESULTADO
    // ============================================
    @PostMapping("/guardar")
    public String guardarResultado(@ModelAttribute("resultado") Resultado resultado,
                                   RedirectAttributes redirectAttributes) {

        Resultado guardado = resultadoRepository.save(resultado);

        redirectAttributes.addFlashAttribute("mensaje", "Resultado guardado correctamente");

        return "redirect:/web/resultados/ver/" + guardado.getId();
    }

    // ============================================
    // VER DETALLE DE UN RESULTADO
    // ============================================
    @GetMapping("/ver/{id}")
    public String verResultado(@PathVariable Long id, Model model) {

        Resultado resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));

        model.addAttribute("resultado", resultado);
        model.addAttribute("estudiante", resultado.getEstudiante());

        return "resultado_detalle";
    }
}
