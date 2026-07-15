package com.fakultet.schedulingservice.controller;

import com.fakultet.schedulingservice.dto.TerminRequest;
import com.fakultet.schedulingservice.model.Termin;
import com.fakultet.schedulingservice.service.TerminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/termini")
public class TerminController {

    private final TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @GetMapping
    public List<Termin> getAll() {
        return terminService.getAll();
    }

    @GetMapping("/{id}")
    public Termin getById(@PathVariable Integer id) {
        return terminService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Termin> create(@Valid @RequestBody TerminRequest request) {
        Termin kreiran = terminService.create(
                request.getUcionicaId(), request.getDanUNedelji(),
                request.getVremeOd(), request.getVremeDo());
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }
}
