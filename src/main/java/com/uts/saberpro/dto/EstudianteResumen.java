package com.uts.saberpro.dto;

import com.uts.saberpro.model.Estudiante;

public class EstudianteResumen {

    private Estudiante estudiante;
    private long totalResultados;
    private double promedio;

    public EstudianteResumen(Estudiante estudiante, long totalResultados, double promedio) {
        this.estudiante = estudiante;
        this.totalResultados = totalResultados;
        this.promedio = promedio;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public long getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(long totalResultados) {
        this.totalResultados = totalResultados;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
