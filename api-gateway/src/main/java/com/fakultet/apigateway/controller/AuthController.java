package com.fakultet.apigateway.controller;

import com.fakultet.apigateway.client.UsersServiceClient;
import com.fakultet.apigateway.dto.KorisnikInfo;
import com.fakultet.apigateway.dto.LoginRequest;
import com.fakultet.apigateway.dto.LoginResponse;
import com.fakultet.apigateway.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersServiceClient usersServiceClient;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UsersServiceClient usersServiceClient, JwtUtil jwtUtil) {
        this.usersServiceClient = usersServiceClient;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody LoginRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return Mono.just(ResponseEntity.badRequest()
                    .body(Map.of("error", "email i password su obavezni")));
        }

        return usersServiceClient.getByEmail(request.getEmail())
                .map(korisnik -> proveriLozinku(korisnik, request.getPassword()))
                .defaultIfEmpty(pogresniKredencijali());
    }

    private ResponseEntity<Object> proveriLozinku(KorisnikInfo korisnik, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, korisnik.getPassword())) {
            return pogresniKredencijali();
        }

        String token = jwtUtil.generateToken(korisnik.getKorisnikId(), korisnik.getEmail(), korisnik.getRole());

        LoginResponse response = new LoginResponse(
                token, korisnik.getKorisnikId(), korisnik.getEmail(), korisnik.getRole());

        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Object> pogresniKredencijali() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "pogresan email ili lozinka"));
    }
}
