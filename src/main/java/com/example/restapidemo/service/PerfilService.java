package com.example.restapidemo.service;

import com.example.restapidemo.service.UserService;
import com.example.restapidemo.model.Perfil;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final List<Perfil> perfiles = new ArrayList<>();
    private Long nextId = 1L;

    private final UserService userService;

    public PerfilService(UserService userService) {
        this.userService = userService;

        // Datos iniciales
        perfiles.add(new Perfil(nextId++, "Administrador", "Acceso total"));
        perfiles.add(new Perfil(nextId++, "Analista", "Acceso a informes"));
        perfiles.add(new Perfil(nextId++, "Supervisor", "Gestión parcial"));
    }

    public List<Perfil> findAll() {
        return perfiles;
    }

    public Optional<Perfil> findById(Long id) {
        return perfiles.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Perfil create(Perfil perfil) {
        perfil.setId(nextId++);
        perfiles.add(perfil);
        return perfil;
    }

    public Optional<Perfil> update(Long id, Perfil perfilActualizado) {
        return findById(id).map(p -> {
            p.setNombre(perfilActualizado.getNombre());
            p.setDescripcion(perfilActualizado.getDescripcion());
            return p;
        });
    }

    public boolean existsById(Long id) {
        return perfiles.stream().anyMatch(p -> p.getId().equals(id));
    }

    public void delete(Long id) {

        // 👉 Validar si algún usuario usa este perfil
        boolean enUso = userService.findAll().stream()
                .anyMatch(u -> u.getPerfilId() != null && u.getPerfilId().equals(id));

        if (enUso) {
            throw new IllegalStateException(
                    "No se puede eliminar el perfil porque está asignado a uno o más usuarios."
            );
        }

        perfiles.removeIf(p -> p.getId().equals(id));
    }
}
