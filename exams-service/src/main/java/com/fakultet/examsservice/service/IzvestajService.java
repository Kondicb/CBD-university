package com.fakultet.examsservice.service;

import com.fakultet.examsservice.dto.KonacnaOcenaResponse;
import com.fakultet.examsservice.model.DomaciZadatak;
import com.fakultet.examsservice.model.Predaja;
import com.fakultet.examsservice.model.PrijavaIspita;
import com.fakultet.examsservice.repository.DomaciZadatakRepository;
import com.fakultet.examsservice.repository.PredajaRepository;
import com.fakultet.examsservice.repository.PrijavaIspitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IzvestajService {

    private final PrijavaIspitaRepository prijavaIspitaRepository;
    private final DomaciZadatakRepository zadatakRepository;
    private final PredajaRepository predajaRepository;

    public IzvestajService(PrijavaIspitaRepository prijavaIspitaRepository,
                            DomaciZadatakRepository zadatakRepository,
                            PredajaRepository predajaRepository) {
        this.prijavaIspitaRepository = prijavaIspitaRepository;
        this.zadatakRepository = zadatakRepository;
        this.predajaRepository = predajaRepository;
    }


    public KonacnaOcenaResponse getKonacnaOcena(Integer studentId, Integer predmetId) {

        List<PrijavaIspita> prijave = prijavaIspitaRepository.findById_StudentId(studentId);

        Integer ocenaIspita = prijave.stream()
                .filter(p -> p.getIspit().getPredmetId().equals(predmetId))
                .filter(p -> p.getOcena() != null)
                .map(PrijavaIspita::getOcena)
                .reduce((first, second) -> second) // uzmi poslednju
                .orElse(null);


        List<DomaciZadatak> zadaciPredmeta = zadatakRepository.findByPredmetId(predmetId);
        Set<Integer> zadatakIds = zadaciPredmeta.stream()
                .map(DomaciZadatak::getZadatakId)
                .collect(Collectors.toSet());


        int ukupnoBodova = predajaRepository.findById_StudentId(studentId).stream()
                .filter(p -> zadatakIds.contains(p.getId().getZadatakId()))
                .map(Predaja::getBodovi)
                .filter(b -> b != null)
                .mapToInt(Integer::intValue)
                .sum();

        String napomena = ocenaIspita == null
                ? "Student jos nije polozio ispit iz ovog predmeta"
                : "Konacna ocena formirana na osnovu ispita i domacih zadataka";

        return new KonacnaOcenaResponse(studentId, predmetId, ocenaIspita, ukupnoBodova, napomena);
    }
}
