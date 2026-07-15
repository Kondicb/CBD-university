package com.fakultet.examsservice.controller;

import com.fakultet.examsservice.dto.BoduRequest;
import com.fakultet.examsservice.dto.ZadatakRequest;
import com.fakultet.examsservice.model.DomaciZadatak;
import com.fakultet.examsservice.model.Predaja;
import com.fakultet.examsservice.service.DomaciZadatakService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zadaci")
public class DomaciZadatakController {

    private final DomaciZadatakService zadatakService;

    public DomaciZadatakController(DomaciZadatakService zadatakService) {
        this.zadatakService = zadatakService;
    }

    @GetMapping("/predmet/{predmetId}")
    public List<DomaciZadatak> getByPredmet(@PathVariable Integer predmetId) {
        return zadatakService.getByPredmet(predmetId);
    }

    @PostMapping
    public ResponseEntity<DomaciZadatak> create(@Valid @RequestBody ZadatakRequest request) {
        DomaciZadatak kreiran = zadatakService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }
    @PostMapping("/{zadatakId}/predaja/{studentId}")
    public ResponseEntity<Predaja> predaj(@PathVariable Integer zadatakId, @PathVariable Integer studentId) {
        Predaja predaja = zadatakService.predaj(studentId, zadatakId);
        return ResponseEntity.status(HttpStatus.CREATED).body(predaja);
    }

    @PutMapping("/{zadatakId}/bodovi/{studentId}")
    public Predaja oceniPredaju(@PathVariable Integer zadatakId, @PathVariable Integer studentId,
                                 @Valid @RequestBody BoduRequest request) {
        return zadatakService.oceniPredaju(studentId, zadatakId, request.getBodovi());
    }
}
