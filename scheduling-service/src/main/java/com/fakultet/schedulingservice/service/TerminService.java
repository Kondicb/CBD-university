package com.fakultet.schedulingservice.service;

import com.fakultet.schedulingservice.exception.BusinessRuleViolationException;
import com.fakultet.schedulingservice.exception.ResourceNotFoundException;
import com.fakultet.schedulingservice.model.Termin;
import com.fakultet.schedulingservice.model.Ucionica;
import com.fakultet.schedulingservice.repository.TerminRepository;
import com.fakultet.schedulingservice.repository.UcionicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminService {

    private final TerminRepository terminRepository;
    private final UcionicaRepository ucionicaRepository;

    public TerminService(TerminRepository terminRepository, UcionicaRepository ucionicaRepository) {
        this.terminRepository = terminRepository;
        this.ucionicaRepository = ucionicaRepository;
    }

    public List<Termin> getAll() {
        return terminRepository.findAll();
    }

    public Termin getById(Integer id) {
        return terminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Termin sa id=" + id + " ne postoji"));
    }

    public Termin create(Integer ucionicaId, String danUNedelji, java.time.LocalTime vremeOd,
                          java.time.LocalTime vremeDo) {

        if (!vremeOd.isBefore(vremeDo)) {
            throw new BusinessRuleViolationException("vremeOd mora biti pre vremeDo");
        }

        Ucionica ucionica = ucionicaRepository.findById(ucionicaId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ucionica sa id=" + ucionicaId + " ne postoji"));

        List<Termin> postojeciTermini = terminRepository
                .findByUcionica_UcionicaIdAndDanUNedelji(ucionicaId, danUNedelji);

        boolean preklapanje = postojeciTermini.stream().anyMatch(t ->
                vremeOd.isBefore(t.getVremeDo()) && t.getVremeOd().isBefore(vremeDo));

        if (preklapanje) {
            throw new BusinessRuleViolationException(String.format(
                    "Ucionica '%s' je vec zauzeta %s u terminu koji se poklapa sa %s-%s",
                    ucionica.getOznaka(), danUNedelji, vremeOd, vremeDo));
        }

        Termin termin = new Termin();
        termin.setDanUNedelji(danUNedelji);
        termin.setVremeOd(vremeOd);
        termin.setVremeDo(vremeDo);
        termin.setUcionica(ucionica);

        return terminRepository.save(termin);
    }
}
