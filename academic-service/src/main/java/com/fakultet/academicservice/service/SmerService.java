package com.fakultet.academicservice.service;

import com.fakultet.academicservice.dto.PlanGodineResponse;
import com.fakultet.academicservice.dto.PredmetResponse;
import com.fakultet.academicservice.exception.ResourceNotFoundException;
import com.fakultet.academicservice.model.Predmet;
import com.fakultet.academicservice.model.Smer;
import com.fakultet.academicservice.repository.PredmetRepository;
import com.fakultet.academicservice.repository.SmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmerService {

    private final SmerRepository smerRepository;
    private final PredmetRepository predmetRepository;

    public SmerService(SmerRepository smerRepository, PredmetRepository predmetRepository) {
        this.smerRepository = smerRepository;
        this.predmetRepository = predmetRepository;
    }

    public List<Smer> getAll() {
        return smerRepository.findAll();
    }

    public Smer getById(Integer id) {
        return smerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Smer sa id=" + id + " ne postoji"));
    }

    public Smer create(Smer smer) {
        return smerRepository.save(smer);
    }

    public PlanGodineResponse getPlanZaGodinu(Integer smerId, Integer godina) {
        getById(smerId);

        List<Predmet> predmeti = predmetRepository.findBySmer_SmerIdAndGodina(smerId, godina);

        List<PredmetResponse> predmetResponses = predmeti.stream()
                .map(p -> new PredmetResponse(
                        p.getPredmetId(), p.getNaziv(), p.getEspb(),
                        p.getSmer().getSmerId(), p.getGodina()))
                .collect(Collectors.toList());

        int ukupnoEspb = predmeti.stream().mapToInt(Predmet::getEspb).sum();

        return new PlanGodineResponse(godina, predmetResponses, ukupnoEspb);
    }
}
