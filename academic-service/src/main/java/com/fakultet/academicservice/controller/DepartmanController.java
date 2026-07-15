package com.fakultet.academicservice.controller;

import com.fakultet.academicservice.model.Departman;
import com.fakultet.academicservice.service.DepartmanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departmani")
public class DepartmanController {

    private final DepartmanService departmanService;

    public DepartmanController(DepartmanService departmanService) {
        this.departmanService = departmanService;
    }

    @GetMapping
    public List<Departman> getAll() {
        return departmanService.getAll();
    }

    @GetMapping("/{id}")
    public Departman getById(@PathVariable Integer id) {
        return departmanService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Departman> create(@Valid @RequestBody Departman departman) {
        Departman kreiran = departmanService.create(departman);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        departmanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
