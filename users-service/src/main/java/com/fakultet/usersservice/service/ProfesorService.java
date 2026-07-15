package com.fakultet.usersservice.service;

import com.fakultet.usersservice.exception.ResourceNotFoundException;
import com.fakultet.usersservice.model.Profesor;
import com.fakultet.usersservice.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> getAll() {
        return profesorRepository.findAll();
    }

    public Profesor getById(Integer id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor sa id=" + id + " ne postoji"));
    }

    public List<Profesor> getByDepartman(Integer departmanId) {
        return profesorRepository.findByDepartmanId(departmanId);
    }
}
