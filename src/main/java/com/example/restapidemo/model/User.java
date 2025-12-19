package com.example.restapidemo.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer edad;

    // Datos adicionales
    private String direccion;
    private String ciudad;
    private String pais;
    private String cargo;
    private String nivelEstudios;
    private String sexo;

    // Cada usuario tiene un perfil
    private Long perfilId;

    // Muchos a muchos con centros
    private List<Long> centrosIds = new ArrayList<>();

    // ======================
    // CONSTRUCTORES
    // ======================
    public User() {}

    public User(Long id,
                String nombre,
                String apellido,
                String email,
                Integer edad,
                Long perfilId) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.perfilId = perfilId;
    }

    // ======================
    // GETTERS & SETTERS
    // ======================
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(String nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public List<Long> getCentrosIds() {
        return centrosIds;
    }

    public void setCentrosIds(List<Long> centrosIds) {
        this.centrosIds = centrosIds;
    }
}
