package com.fakultet.academicservice.controller;

import com.fakultet.academicservice.model.Materijal;
import com.fakultet.academicservice.service.MaterijalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materijali")
public class MaterijalController {

    private final MaterijalService materijalService;

    public MaterijalController(MaterijalService materijalService) {
        this.materijalService = materijalService;
    }

    @GetMapping("/predmet/{predmetId}")
    public List<Materijal> getByPredmet(@PathVariable Integer predmetId) {
        return materijalService.getByPredmet(predmetId);
    }

    @PostMapping
    public ResponseEntity<Materijal> create(@RequestBody Map<String, Object> body) {
        Integer predmetId = (Integer) body.get("predmetId");
        String naziv = (String) body.get("naziv");
        String tip = (String) body.get("tip");

        Materijal kreiran = materijalService.create(predmetId, naziv, tip);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }
}
