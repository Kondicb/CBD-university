package com.fakultet.usersservice.controller;

import com.fakultet.usersservice.dto.RegistracijaRequest;
import com.fakultet.usersservice.model.Korisnik;
import com.fakultet.usersservice.service.KorisnikService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping("/{id}")
    public Korisnik getById(@PathVariable Integer id) {
        return korisnikService.getById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Korisnik> getByEmail(@PathVariable String email) {
        return korisnikService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/registracija")
    public ResponseEntity<Korisnik> registruj(@Valid @RequestBody RegistracijaRequest request) {
        Korisnik kreiran = korisnikService.registruj(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(kreiran);
    }
}
