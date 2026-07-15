package com.fakultet.schedulingservice.service;

import com.fakultet.schedulingservice.exception.ResourceNotFoundException;
import com.fakultet.schedulingservice.model.Semestar;
import com.fakultet.schedulingservice.repository.SemestarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemestarService {

    private final SemestarRepository semestarRepository;

    public SemestarService(SemestarRepository semestarRepository) {
        this.semestarRepository = semestarRepository;
    }

    public List<Semestar> getAll() {
        return semestarRepository.findAll();
    }

    public Semestar getById(Integer id) {
        return semestarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semestar sa id=" + id + " ne postoji"));
    }

    public Semestar create(Semestar semestar) {
        return semestarRepository.save(semestar);
    }
}
