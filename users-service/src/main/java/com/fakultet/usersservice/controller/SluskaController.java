package com.fakultet.usersservice.controller;

import com.fakultet.usersservice.dto.UpisaniPredmetResponse;
import com.fakultet.usersservice.model.Sluska;
import com.fakultet.usersservice.service.SluskaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sluska")
public class SluskaController {

    private final SluskaService sluskaService;

    public SluskaController(SluskaService sluskaService) {
        this.sluskaService = sluskaService;
    }

    @PostMapping("/{studentId}/predmet/{predmetId}")
    public ResponseEntity<Sluska> upisi(@PathVariable Integer studentId, @PathVariable Integer predmetId) {
        Sluska sluska = sluskaService.upisiStudentaNaPredmet(studentId, predmetId);
        return ResponseEntity.status(HttpStatus.CREATED).body(sluska);
    }

    @GetMapping("/predmet/{predmetId}/broj-studenata")
    public long brojStudenata(@PathVariable Integer predmetId) {
        return sluskaService.getBrojUpisanihStudenata(predmetId);
    }

    @GetMapping("/{studentId}/upisan/{predmetId}")
    public boolean jeUpisan(@PathVariable Integer studentId, @PathVariable Integer predmetId) {
        return sluskaService.jeUpisan(studentId, predmetId);
    }

    @DeleteMapping("/{studentId}/predmet/{predmetId}")
    public ResponseEntity<Void> odjavi(@PathVariable Integer studentId, @PathVariable Integer predmetId) {
        sluskaService.odjaviStudenta(studentId, predmetId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/predmeti")
    public List<UpisaniPredmetResponse> mojiPredmeti(@PathVariable Integer studentId) {
        return sluskaService.getUpisaniPredmeti(studentId);
    }
}
