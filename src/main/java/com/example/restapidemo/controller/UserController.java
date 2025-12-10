package com.example.restapidemo.controller;

import com.example.restapidemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public UserController() {

        users.add(new User(
                nextId++,              // id
                "Juan",                // nombres
                "Pérez",               // apellidos
                "juan@example.com",    // email
                30,                    // edad
                "m",                   // sexo
                "Diagonal 100",        // direccion
                "Barcelona",           // ciudad
                "España",              // pais
                "Manager",             // cargo
                "Doctorado",           // nivelEstudios
                1L,                    // rolId
                "Administrador"        // rolNombre
        ));

        users.add(new User(
                nextId++,
                "María",
                "García",
                "maria@example.com",
                25,
                "f",
                "Calle Luna 45",
                "Madrid",
                "España",
                "Analista",
                "Master",
                2L,
                "Empleado"
        ));

        users.add(new User(
                nextId++,
                "Carlos",
                "López",
                "carlos@example.com",
                35,
                "m",
                "Av. Sol 22",
                "Sevilla",
                "España",
                "Supervisor",
                "Licenciatura",
                3L,
                "Invitado"
        ));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        user.setId(nextId++);
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User data) {

        Optional<User> u = users.stream().filter(x -> x.getId().equals(id)).findFirst();

        if (u.isEmpty()) return ResponseEntity.notFound().build();

        User user = u.get();

        user.setNombres(data.getNombres());
        user.setApellidos(data.getApellidos());
        user.setEmail(data.getEmail());
        user.setEdad(data.getEdad());
        user.setSexo(data.getSexo());
        user.setDireccion(data.getDireccion());
        user.setCiudad(data.getCiudad());
        user.setPais(data.getPais());
        user.setCargo(data.getCargo());
        user.setNivelEstudios(data.getNivelEstudios());
        user.setRolId(data.getRolId());
        user.setRolNombre(data.getRolNombre());

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean assigned = users.removeIf(u -> u.getId().equals(id));

        if (assigned) return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
