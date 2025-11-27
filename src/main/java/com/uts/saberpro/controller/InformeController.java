package com.uts.saberpro.controller;

import com.uts.saberpro.dto.EstudianteResumen;
import com.uts.saberpro.model.Estudiante;
import com.uts.saberpro.model.Resultado;
import com.uts.saberpro.service.EstudianteService;
import com.uts.saberpro.service.ResultadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/informes")
public class InformeController {

    private final EstudianteService estudianteService;
    private final ResultadoService resultadoService;

    public InformeController(EstudianteService estudianteService,
                             ResultadoService resultadoService) {
        this.estudianteService = estudianteService;
        this.resultadoService = resultadoService;
    }

    // 1) INFORME DE ALUMNOS (lista general)
    @GetMapping("/alumnos")
    public String informeAlumnos(Model model) {

        List<Estudiante> estudiantes = estudianteService.listar();

        List<EstudianteResumen> resumen = estudiantes.stream()
                .map(est -> {
                    List<Resultado> res = resultadoService.listarPorEstudiante(est);
                    long total = res.size();
                    double prom = total == 0 ? 0.0 :
                            res.stream().collect(Collectors.averagingDouble(Resultado::getPuntaje));
                    return new EstudianteResumen(est, total, prom);
                })
                .collect(Collectors.toList());

        model.addAttribute("resumen", resumen);
        return "informe-alumnos";
    }

    // 2) INFORME DETALLADO por alumno
    @GetMapping("/alumno/{id}")
    public String informeDetallado(@PathVariable Long id, Model model) {

        Estudiante est = estudianteService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<Resultado> resultados = resultadoService.listarPorEstudiante(est);
        double promedio = resultadoService.promedioEstudiante(est);

        model.addAttribute("estudiante", est);
        model.addAttribute("resultados", resultados);
        model.addAttribute("promedio", promedio);

        return "informe-detallado";
    }

    // 3) INFORME DE BENEFICIOS (promedio >= 150)
    @GetMapping("/beneficios")
    public String informeBeneficios(Model model) {

        double minimoBeneficio = 150.0; // regla ejemplo

        List<Estudiante> estudiantes = estudianteService.listar();

        List<EstudianteResumen> beneficiarios = estudiantes.stream()
                .map(est -> {
                    List<Resultado> res = resultadoService.listarPorEstudiante(est);
                    long total = res.size();
                    double prom = total == 0 ? 0.0 :
                            res.stream().collect(Collectors.averagingDouble(Resultado::getPuntaje));
                    return new EstudianteResumen(est, total, prom);
                })
                .filter(r -> r.getPromedio() >= minimoBeneficio)
                .collect(Collectors.toList());

        model.addAttribute("beneficiarios", beneficiarios);
        model.addAttribute("minimo", minimoBeneficio);

        return "informe-beneficios";
    }
}
