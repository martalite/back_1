package com.example.restapidemo.controller;

import com.example.restapidemo.model.Centro;
import com.example.restapidemo.service.CentroService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
@CrossOrigin(origins = "*")
public class CentroController {

    private final CentroService centroService;

    public CentroController(CentroService centroService) {
        this.centroService = centroService;
    }

    @GetMapping
    public List<Centro> getAll() {
        return centroService.findAll();
    }
}
