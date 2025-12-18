package com.example.restapidemo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nuhsa;

    private String codigoTao;
    private String sip2;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    private String dni;

    private String modalidadControl;

    // 🔗 MUCHOS PACIENTES → UN CENTRO
    @ManyToOne
    @JoinColumn(name = "centro_id")
    @JsonIgnoreProperties("pacientes")
    private Centro centro;

    // 🔹 Constructor vacío (OBLIGATORIO para JPA)
    public Paciente() {}

    // 🔹 Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNuhsa() { return nuhsa; }
    public void setNuhsa(String nuhsa) { this.nuhsa = nuhsa; }

    public String getCodigoTao() { return codigoTao; }
    public void setCodigoTao(String codigoTao) { this.codigoTao = codigoTao; }

    public String getSip2() { return sip2; }
    public void setSip2(String sip2) { this.sip2 = sip2; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getModalidadControl() { return modalidadControl; }
    public void setModalidadControl(String modalidadControl) { this.modalidadControl = modalidadControl; }

    public Centro getCentro() { return centro; }
    public void setCentro(Centro centro) { this.centro = centro; }
}
