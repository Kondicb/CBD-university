package com.fakultet.usersservice.service;

import com.fakultet.usersservice.dto.RegistracijaRequest;
import com.fakultet.usersservice.exception.BusinessRuleViolationException;
import com.fakultet.usersservice.exception.ResourceNotFoundException;
import com.fakultet.usersservice.model.Korisnik;
import com.fakultet.usersservice.model.Profesor;
import com.fakultet.usersservice.model.Student;
import com.fakultet.usersservice.repository.KorisnikRepository;
import com.fakultet.usersservice.repository.ProfesorRepository;
import com.fakultet.usersservice.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final StudentRepository studentRepository;
    private final ProfesorRepository profesorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public KorisnikService(KorisnikRepository korisnikRepository,
                            StudentRepository studentRepository,
                            ProfesorRepository profesorRepository) {
        this.korisnikRepository = korisnikRepository;
        this.studentRepository = studentRepository;
        this.profesorRepository = profesorRepository;
    }

    public Korisnik getById(Integer id) {
        return korisnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa id=" + id + " ne postoji"));
    }

    public java.util.Optional<Korisnik> findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    public Korisnik registruj(RegistracijaRequest req) {
        if (korisnikRepository.existsByEmail(req.getEmail())) {
            throw new BusinessRuleViolationException(
                    "Korisnik sa email adresom '" + req.getEmail() + "' vec postoji");
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(req.getIme());
        korisnik.setEmail(req.getEmail());
        korisnik.setPassword(passwordEncoder.encode(req.getPassword()));
        korisnik.setRole(req.getRole());

        korisnik = korisnikRepository.save(korisnik);

        if ("STUDENT".equals(req.getRole())) {
            if (req.getSmerId() == null || req.getGodinaUpisa() == null) {
                throw new BusinessRuleViolationException(
                        "Za registraciju studenta su obavezni smerId i godinaUpisa");
            }
            Student student = new Student();
            student.setKorisnik(korisnik);
            student.setPrezime(req.getPrezime());
            student.setGodinaUpisa(req.getGodinaUpisa());
            student.setSmerId(req.getSmerId());
            studentRepository.save(student);

        } else if ("PROFESOR".equals(req.getRole())) {
            if (req.getDepartmanId() == null) {
                throw new BusinessRuleViolationException(
                        "Za registraciju profesora je obavezan departmanId");
            }
            Profesor profesor = new Profesor();
            profesor.setKorisnik(korisnik);
            profesor.setPrezime(req.getPrezime());
            profesor.setTitula(req.getTitula());
            profesor.setKabinet(req.getKabinet());
            profesor.setDepartmanId(req.getDepartmanId());
            profesorRepository.save(profesor);
        }
        return korisnik;
    }
}
