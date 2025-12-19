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

        // =========================
        // DATOS DE PRUEBA COMPLETOS
        // =========================

        User u1 = new User(nextId++, "Juan", "Pérez", "juan@example.com", 30, 1L);
        u1.setDireccion("Calle Mayor 123");
        u1.setCiudad("Madrid");
        u1.setPais("España");
        u1.setCargo("Administrador de Sistemas");
        u1.setNivelEstudios("Universitario");
        u1.setSexo("Masculino");
        users.add(u1);

        User u2 = new User(nextId++, "María", "García", "maria@example.com", 25, 2L);
        u2.setDireccion("Av. Diagonal 456");
        u2.setCiudad("Barcelona");
        u2.setPais("España");
        u2.setCargo("Analista de Datos");
        u2.setNivelEstudios("Máster");
        u2.setSexo("Femenino");
        users.add(u2);

        User u3 = new User(nextId++, "Carlos", "López", "carlos@example.com", 35, 3L);
        u3.setDireccion("C/ Gran Vía 89");
        u3.setCiudad("Valencia");
        u3.setPais("España");
        u3.setCargo("Supervisor");
        u3.setNivelEstudios("FP Superior");
        u3.setSexo("Masculino");
        users.add(u3);
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

            // 🔥 NUEVOS CAMPOS
            u.setDireccion(userActualizado.getDireccion());
            u.setCiudad(userActualizado.getCiudad());
            u.setPais(userActualizado.getPais());
            u.setCargo(userActualizado.getCargo());
            u.setNivelEstudios(userActualizado.getNivelEstudios());
            u.setSexo(userActualizado.getSexo());

            // 🔥 CENTROS
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
