package com.fakultet.schedulingservice.service;

import com.fakultet.schedulingservice.exception.ResourceNotFoundException;
import com.fakultet.schedulingservice.model.Ucionica;
import com.fakultet.schedulingservice.repository.UcionicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UcionicaService {

    private final UcionicaRepository ucionicaRepository;

    public UcionicaService(UcionicaRepository ucionicaRepository) {
        this.ucionicaRepository = ucionicaRepository;
    }

    public List<Ucionica> getAll() {
        return ucionicaRepository.findAll();
    }

    public Ucionica getById(Integer id) {
        return ucionicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ucionica sa id=" + id + " ne postoji"));
    }

    public Ucionica create(Ucionica ucionica) {
        return ucionicaRepository.save(ucionica);
    }
}
