package com.fakultet.schedulingservice.controller;

import com.fakultet.schedulingservice.model.Ucionica;
import com.fakultet.schedulingservice.service.UcionicaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ucionice")
public class UcionicaController {

    private final UcionicaService ucionicaService;

    public UcionicaController(UcionicaService ucionicaService) {
        this.ucionicaService = ucionicaService;
    }

    @GetMapping
    public List<Ucionica> getAll() {
        return ucionicaService.getAll();
    }

    @GetMapping("/{id}")
    public Ucionica getById(@PathVariable Integer id) {
        return ucionicaService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Ucionica> create(@Valid @RequestBody Ucionica ucionica) {
        Ucionica kreirana = ucionicaService.create(ucionica);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreirana);
    }
}
