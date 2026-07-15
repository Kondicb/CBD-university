package com.fakultet.examsservice.controller;

import com.fakultet.examsservice.dto.IspitRequest;
import com.fakultet.examsservice.dto.OcenaRequest;
import com.fakultet.examsservice.model.Ispit;
import com.fakultet.examsservice.model.PrijavaIspita;
import com.fakultet.examsservice.service.IspitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ispiti")
public class IspitController {

    private final IspitService ispitService;

    public IspitController(IspitService ispitService) {
        this.ispitService = ispitService;
    }

    @GetMapping
    public List<Ispit> getAll() {
        return ispitService.getAll();
    }

    @GetMapping("/{id}")
    public Ispit getById(@PathVariable Integer id) {
        return ispitService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Ispit> create(@Valid @RequestBody IspitRequest request) {
        Ispit kreiran = ispitService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }
    @PostMapping("/{ispitId}/prijava/{studentId}")
    public ResponseEntity<PrijavaIspita> prijaviSe(@PathVariable Integer ispitId,
                                                    @PathVariable Integer studentId) {
        PrijavaIspita prijava = ispitService.prijaviNaIspit(studentId, ispitId);
        return ResponseEntity.status(HttpStatus.CREATED).body(prijava);
    }

    @PutMapping("/{ispitId}/ocena/{studentId}")
    public PrijavaIspita unesiOcenu(@PathVariable Integer ispitId, @PathVariable Integer studentId,
                                     @Valid @RequestBody OcenaRequest request) {
        return ispitService.unesiOcenu(studentId, ispitId, request.getOcena());
    }
}
