package com.fakultet.academicservice.controller;

import com.fakultet.academicservice.dto.PredmetRequest;
import com.fakultet.academicservice.dto.PredmetResponse;
import com.fakultet.academicservice.model.Predmet;
import com.fakultet.academicservice.service.PredmetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
public class PredmetController {

    private final PredmetService predmetService;

    public PredmetController(PredmetService predmetService) {
        this.predmetService = predmetService;
    }

    @GetMapping
    public List<Predmet> getAll() {
        return predmetService.getAll();
    }

    @GetMapping("/{id}")
    public PredmetResponse getById(@PathVariable Integer id) {
        return predmetService.getResponseById(id);
    }

    @PostMapping
    public ResponseEntity<Predmet> create(@Valid @RequestBody PredmetRequest request) {
        Predmet kreiran = predmetService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        predmetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
