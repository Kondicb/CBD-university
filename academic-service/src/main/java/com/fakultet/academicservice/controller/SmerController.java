package com.fakultet.academicservice.controller;

import com.fakultet.academicservice.dto.PlanGodineResponse;
import com.fakultet.academicservice.model.Smer;
import com.fakultet.academicservice.service.SmerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/smerovi")
public class SmerController {

    private final SmerService smerService;

    public SmerController(SmerService smerService) {
        this.smerService = smerService;
    }

    @GetMapping
    public List<Smer> getAll() {
        return smerService.getAll();
    }

    @GetMapping("/{id}")
    public Smer getById(@PathVariable Integer id) {
        return smerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Smer> create(@Valid @RequestBody Smer smer) {
        Smer kreiran = smerService.create(smer);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }

    @GetMapping("/{id}/plan-studija/{godina}")
    public PlanGodineResponse getPlanZaGodinu(@PathVariable Integer id, @PathVariable Integer godina) {
        return smerService.getPlanZaGodinu(id, godina);
    }
}
