package com.example.restapidemo.controller;

import com.example.restapidemo.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "API para gestionar usuarios")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    // --------------------------------------------------------
    // CONSTRUCTOR — Lista inicial con todos los campos llenos
    // --------------------------------------------------------
    public UserController() {

        users.add(new User(
                nextId++, "Juan", "Pérez", "juan@example.com", 30,
                "m", "Calle 123", "Madrid", "España", "Developer", "Master"
        ));

        users.add(new User(
                nextId++, "María", "García", "maria@example.com", 25,
                "f", "Av. Libertad 45", "Barcelona", "España", "Analista", "Grado"
        ));

        users.add(new User(
                nextId++, "Carlos", "López", "carlos@example.com", 35,
                "m", "Diagonal 100", "Barcelona", "España", "Manager", "Doctorado"
        ));
    }

    // --------------------------------------------------------
    // GET  — Obtener todos los usuarios
    // --------------------------------------------------------
    @GetMapping
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    // --------------------------------------------------------
    // GET por ID
    // --------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --------------------------------------------------------
    // SEARCH — Buscar por nombre o apellidos
    // --------------------------------------------------------
    @GetMapping("/search")
    @Operation(summary = "Buscar usuarios por nombre")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return ResponseEntity.ok(users);
        }

        String filter = nombre.toLowerCase();

        List<User> filtered = users.stream()
                .filter(u ->
                        u.getNombres().toLowerCase().contains(filter) ||
                        u.getApellidos().toLowerCase().contains(filter)
                )
                .toList();

        return ResponseEntity.ok(filtered);
    }

    // --------------------------------------------------------
    // POST — Crear usuario
    // --------------------------------------------------------
    @PostMapping
    @Operation(summary = "Crear un usuario")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        user.setId(nextId++);
        users.add(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // --------------------------------------------------------
    // PUT — Actualizar usuario COMPLETO
    // --------------------------------------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser) {

        Optional<User> existingUserOpt = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = existingUserOpt.get();

        // Actualizar todos los campos
        user.setNombres(updatedUser.getNombres());
        user.setApellidos(updatedUser.getApellidos());
        user.setEmail(updatedUser.getEmail());
        user.setEdad(updatedUser.getEdad());
        user.setSexo(updatedUser.getSexo());
        user.setDireccion(updatedUser.getDireccion());
        user.setCiudad(updatedUser.getCiudad());
        user.setPais(updatedUser.getPais());
        user.setCargo(updatedUser.getCargo());
        user.setNivelEstudios(updatedUser.getNivelEstudios());

        return ResponseEntity.ok(user);
    }

    // --------------------------------------------------------
    // DELETE — Eliminar usuario
    // --------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        boolean removed = users.removeIf(u -> u.getId().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
