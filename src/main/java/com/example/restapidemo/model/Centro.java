package com.example.restapidemo.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "centros")
public class Centro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // 🔗 UN CENTRO → MUCHOS PACIENTES
    @OneToMany(mappedBy = "centro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Paciente> pacientes;

    // 🔹 Constructor vacío (JPA)
    public Centro() {}

    public Centro(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // 🔹 Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
