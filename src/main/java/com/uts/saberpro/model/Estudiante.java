package com.uts.saberpro.model;

import jakarta.persistence.*;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;      // código del estudiante

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String programa;    // programa académico

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }
}
