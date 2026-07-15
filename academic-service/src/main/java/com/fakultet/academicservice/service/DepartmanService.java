package com.fakultet.academicservice.service;

import com.fakultet.academicservice.exception.ResourceNotFoundException;
import com.fakultet.academicservice.model.Departman;
import com.fakultet.academicservice.repository.DepartmanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmanService {

    private final DepartmanRepository departmanRepository;

    public DepartmanService(DepartmanRepository departmanRepository) {
        this.departmanRepository = departmanRepository;
    }

    public List<Departman> getAll() {
        return departmanRepository.findAll();
    }

    public Departman getById(Integer id) {
        return departmanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departman sa id=" + id + " ne postoji"));
    }

    public Departman create(Departman departman) {
        return departmanRepository.save(departman);
    }

    public void delete(Integer id) {
        Departman departman = getById(id);
        departmanRepository.delete(departman);
    }
}
