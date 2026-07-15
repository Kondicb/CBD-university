package com.fakultet.academicservice.service;

import com.fakultet.academicservice.dto.PredmetRequest;
import com.fakultet.academicservice.dto.PredmetResponse;
import com.fakultet.academicservice.exception.BusinessRuleViolationException;
import com.fakultet.academicservice.exception.ResourceNotFoundException;
import com.fakultet.academicservice.model.Predmet;
import com.fakultet.academicservice.model.Smer;
import com.fakultet.academicservice.repository.PredmetRepository;
import com.fakultet.academicservice.repository.SmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredmetService {

    private static final int MAX_ESPB_PO_GODINI = 60;

    private final PredmetRepository predmetRepository;
    private final SmerRepository smerRepository;

    public PredmetService(PredmetRepository predmetRepository, SmerRepository smerRepository) {
        this.predmetRepository = predmetRepository;
        this.smerRepository = smerRepository;
    }

    public List<Predmet> getAll() {
        return predmetRepository.findAll();
    }

    public Predmet getById(Integer id) {
        return predmetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Predmet sa id=" + id + " ne postoji"));
    }

    public PredmetResponse getResponseById(Integer id) {
        Predmet p = getById(id);
        return new PredmetResponse(p.getPredmetId(), p.getNaziv(), p.getEspb(),
                p.getSmer().getSmerId(), p.getGodina());
    }

    public Predmet create(PredmetRequest request) {
        Smer smer = smerRepository.findById(request.getSmerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Smer sa id=" + request.getSmerId() + " ne postoji"));

        List<Predmet> postojeciPredmeti = predmetRepository
                .findBySmer_SmerIdAndGodina(request.getSmerId(), request.getGodina());

        int trenutnoEspb = postojeciPredmeti.stream().mapToInt(Predmet::getEspb).sum();

        if (trenutnoEspb + request.getEspb() > MAX_ESPB_PO_GODINI) {
            throw new BusinessRuleViolationException(String.format(
                    "Dodavanje predmeta '%s' (%d ESPB) bi premasilo limit od %d ESPB za godinu %d "
                            + "na smeru '%s'. Trenutno je vec upisano %d ESPB.",
                    request.getNaziv(), request.getEspb(), MAX_ESPB_PO_GODINI,
                    request.getGodina(), smer.getNaziv(), trenutnoEspb));
        }

        Predmet predmet = new Predmet();
        predmet.setNaziv(request.getNaziv());
        predmet.setEspb(request.getEspb());
        predmet.setSmer(smer);
        predmet.setGodina(request.getGodina());

        return predmetRepository.save(predmet);
    }

    public void delete(Integer id) {
        Predmet predmet = getById(id);
        predmetRepository.delete(predmet);
    }
}
