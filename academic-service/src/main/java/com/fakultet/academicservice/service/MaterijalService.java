package com.fakultet.academicservice.service;

import com.fakultet.academicservice.exception.BusinessRuleViolationException;
import com.fakultet.academicservice.exception.ResourceNotFoundException;
import com.fakultet.academicservice.model.Materijal;
import com.fakultet.academicservice.model.Predmet;
import com.fakultet.academicservice.repository.MaterijalRepository;
import com.fakultet.academicservice.repository.PredmetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MaterijalService {

    private static final Set<String> DOZVOLJENI_TIPOVI =
            Set.of("PREZENTACIJA", "SKRIPTA", "ZADACI", "VIDEO");

    private final MaterijalRepository materijalRepository;
    private final PredmetRepository predmetRepository;

    public MaterijalService(MaterijalRepository materijalRepository, PredmetRepository predmetRepository) {
        this.materijalRepository = materijalRepository;
        this.predmetRepository = predmetRepository;
    }

    public List<Materijal> getByPredmet(Integer predmetId) {
        return materijalRepository.findByPredmet_PredmetId(predmetId);
    }

    public Materijal create(Integer predmetId, String naziv, String tip) {
        if (!DOZVOLJENI_TIPOVI.contains(tip.toUpperCase())) {
            throw new BusinessRuleViolationException(
                    "Nepoznat tip materijala: '" + tip + "'. Dozvoljeni tipovi: " + DOZVOLJENI_TIPOVI);
        }

        Predmet predmet = predmetRepository.findById(predmetId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Predmet sa id=" + predmetId + " ne postoji"));

        Materijal materijal = new Materijal();
        materijal.setNaziv(naziv);
        materijal.setTip(tip.toUpperCase());
        materijal.setPredmet(predmet);

        return materijalRepository.save(materijal);
    }
}
