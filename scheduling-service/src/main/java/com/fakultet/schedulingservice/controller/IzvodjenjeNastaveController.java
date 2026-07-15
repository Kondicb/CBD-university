package com.fakultet.schedulingservice.controller;

import com.fakultet.schedulingservice.dto.IzvodjenjeRequest;
import com.fakultet.schedulingservice.dto.RasporedStavkaResponse;
import com.fakultet.schedulingservice.model.IzvodjenjeNastave;
import com.fakultet.schedulingservice.service.IzvodjenjeNastaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/izvodjenje")
public class IzvodjenjeNastaveController {

    private final IzvodjenjeNastaveService izvodjenjeService;

    public IzvodjenjeNastaveController(IzvodjenjeNastaveService izvodjenjeService) {
        this.izvodjenjeService = izvodjenjeService;
    }

    @PostMapping
    public ResponseEntity<IzvodjenjeNastave> create(@Valid @RequestBody IzvodjenjeRequest request) {
        IzvodjenjeNastave kreirano = izvodjenjeService.create(
                request.getPredmetId(), request.getSemestarId(),
                request.getProfesorId(), request.getTerminId());
        return ResponseEntity.status(HttpStatus.CREATED).body(kreirano);
    }

    @GetMapping("/semestar/{semestarId}/raspored")
    public List<RasporedStavkaResponse> getRaspored(@PathVariable Integer semestarId) {
        return izvodjenjeService.getRasporedZaSemestar(semestarId);
    }
}
