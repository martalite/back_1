package com.example.restapidemo.repository;

import com.example.restapidemo.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByNuhsa(String nuhsa);
}

