package com.example.restapidemo.service;

import com.example.restapidemo.model.Centro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CentroService {

    private final List<Centro> centros = new ArrayList<>();
    private Long nextId = 1L;

    public CentroService() {
        // Datos de ejemplo
        centros.add(new Centro(nextId++, "Centro 1"));
        centros.add(new Centro(nextId++, "Centro 2"));
        centros.add(new Centro(nextId++, "Centro 3"));
        centros.add(new Centro(nextId++, "Hospital Demo"));
    }

    public List<Centro> findAll() {
        return centros;
    }

    public Optional<Centro> findById(Long id) {
        return centros.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
