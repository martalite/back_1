package com.example.restapidemo.controller;

import com.example.restapidemo.service.PerfilService;
import com.example.restapidemo.service.UserService;
import com.example.restapidemo.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final PerfilService perfilService;

    public UserController(UserService userService, PerfilService perfilService) {
        this.userService = userService;
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> userOpt = userService.findById(id);
        return userOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {

        if (user.getPerfilId() == null) {
            return ResponseEntity.badRequest()
                    .body("El perfil es obligatorio.");
        }

        if (!perfilService.existsById(user.getPerfilId())) {
            return ResponseEntity.badRequest()
                    .body("El perfil seleccionado no existe.");
        }

        User creado = userService.create(user);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {

        if (user.getPerfilId() == null) {
            return ResponseEntity.badRequest()
                    .body("El perfil es obligatorio.");
        }

        if (!perfilService.existsById(user.getPerfilId())) {
            return ResponseEntity.badRequest()
                    .body("El perfil seleccionado no existe.");
        }

        Optional<User> actualizado = userService.update(id, user);
        return actualizado.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean eliminado = userService.delete(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
