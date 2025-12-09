package com.example.restapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de Usuario COMPLETO
 * Compatible al 100% con el formulario de ExtJS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String nombres;        // Ej: "Juan"
    private String apellidos;      // Ej: "Pérez"

    private String email;
    private int edad;

    private String sexo;           // m / f
    private String direccion;
    private String ciudad;
    private String pais;

    private String cargo;          // Ej: "Developer"
    private String nivelEstudios;  // Ej: "Master"
}
