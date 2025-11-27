package com.uts.saberpro.model;

import jakarta.persistence.*;

@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación muchos resultados para un estudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    private String modulo;   // Ej: Comunicación, Razonamiento, etc.
    private Double puntaje;  // Ej: 150.0
    private String periodo;  // Ej: 2025-2

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
