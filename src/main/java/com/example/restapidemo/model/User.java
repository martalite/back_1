package com.example.restapidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String nombres;
    private String apellidos;
    private String email;
    private int edad;

    private String sexo;
    private String direccion;
    private String ciudad;
    private String pais;
    private String cargo;
    private String nivelEstudios;

    private Long rolId;       // ID del rol
    private String rolNombre; // Nombre del rol
}
