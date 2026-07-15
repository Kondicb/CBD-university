package com.fakultet.usersservice.controller;

import com.fakultet.usersservice.model.Profesor;
import com.fakultet.usersservice.service.ProfesorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profesori")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> getAll() {
        return profesorService.getAll();
    }

    @GetMapping("/{id}")
    public Profesor getById(@PathVariable Integer id) {
        return profesorService.getById(id);
    }

    @GetMapping("/departman/{departmanId}")
    public List<Profesor> getByDepartman(@PathVariable Integer departmanId) {
        return profesorService.getByDepartman(departmanId);
    }
}
