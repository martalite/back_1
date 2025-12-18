package com.example.restapidemo.controller;

import com.example.restapidemo.model.Paciente;
import com.example.restapidemo.repository.PacienteRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // ✅ GET ALL
    @GetMapping
    public List<Paciente> getAll() {
        return pacienteRepository.findAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST
    @PostMapping
    public Paciente create(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // ✅ PUT
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(
            @PathVariable Long id,
            @RequestBody Paciente paciente) {

        return pacienteRepository.findById(id)
                .map(existing -> {
                    paciente.setId(existing.getId()); // clave
                    return ResponseEntity.ok(pacienteRepository.save(paciente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
