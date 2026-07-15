package com.fakultet.schedulingservice.controller;

import com.fakultet.schedulingservice.model.Semestar;
import com.fakultet.schedulingservice.service.SemestarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semestri")
public class SemestarController {

    private final SemestarService semestarService;

    public SemestarController(SemestarService semestarService) {
        this.semestarService = semestarService;
    }

    @GetMapping
    public List<Semestar> getAll() {
        return semestarService.getAll();
    }

    @GetMapping("/{id}")
    public Semestar getById(@PathVariable Integer id) {
        return semestarService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Semestar> create(@Valid @RequestBody Semestar semestar) {
        Semestar kreiran = semestarService.create(semestar);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }
}
