package com.example.restapidemo.service;

import com.example.restapidemo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public UserService() {

        // DATOS DE PRUEBA
        users.add(new User(nextId++, "Juan", "Pérez", "juan@example.com", 30, 1L));
        users.add(new User(nextId++, "María", "García", "maria@example.com", 25, 2L));
        users.add(new User(nextId++, "Carlos", "López", "carlos@example.com", 35, 3L));
    }

    // =========================
    // CRUD
    // =========================

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public User create(User user) {
        user.setId(nextId++);
        if (user.getCentrosIds() == null) {
            user.setCentrosIds(new ArrayList<>());
        }
        users.add(user);
        return user;
    }

    public Optional<User> update(Long id, User userActualizado) {
        return findById(id).map(u -> {

            u.setNombre(userActualizado.getNombre());
            u.setApellido(userActualizado.getApellido());
            u.setEmail(userActualizado.getEmail());
            u.setEdad(userActualizado.getEdad());
            u.setPerfilId(userActualizado.getPerfilId());

            // 🔥 CLAVE: GUARDAR CENTROS
            u.setCentrosIds(
                userActualizado.getCentrosIds() != null
                    ? userActualizado.getCentrosIds()
                    : new ArrayList<>()
            );

            return u;
        });
    }

    public boolean delete(Long id) {
        return users.removeIf(u -> u.getId().equals(id));
    }
}
