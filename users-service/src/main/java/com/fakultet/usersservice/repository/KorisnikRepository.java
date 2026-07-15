package com.fakultet.usersservice.repository;

import com.fakultet.usersservice.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Optional<Korisnik> findByEmail(String email);
    boolean existsByEmail(String email);
}
