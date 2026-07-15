package com.fakultet.examsservice.service;

import com.fakultet.examsservice.client.AcademicServiceClient;
import com.fakultet.examsservice.client.UsersServiceClient;
import com.fakultet.examsservice.dto.ZadatakRequest;
import com.fakultet.examsservice.exception.BusinessRuleViolationException;
import com.fakultet.examsservice.exception.ResourceNotFoundException;
import com.fakultet.examsservice.model.DomaciZadatak;
import com.fakultet.examsservice.model.Predaja;
import com.fakultet.examsservice.model.PredajaId;
import com.fakultet.examsservice.repository.DomaciZadatakRepository;
import com.fakultet.examsservice.repository.PredajaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DomaciZadatakService {

    private final DomaciZadatakRepository zadatakRepository;
    private final PredajaRepository predajaRepository;
    private final AcademicServiceClient academicServiceClient;
    private final UsersServiceClient usersServiceClient;

    public DomaciZadatakService(DomaciZadatakRepository zadatakRepository,
                                 PredajaRepository predajaRepository,
                                 AcademicServiceClient academicServiceClient,
                                 UsersServiceClient usersServiceClient) {
        this.zadatakRepository = zadatakRepository;
        this.predajaRepository = predajaRepository;
        this.academicServiceClient = academicServiceClient;
        this.usersServiceClient = usersServiceClient;
    }

    public List<DomaciZadatak> getByPredmet(Integer predmetId) {
        return zadatakRepository.findByPredmetId(predmetId);
    }

    public DomaciZadatak getById(Integer id) {
        return zadatakRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zadatak sa id=" + id + " ne postoji"));
    }

    public DomaciZadatak create(ZadatakRequest request) {
        academicServiceClient.getPredmetById(request.getPredmetId());

        DomaciZadatak zadatak = new DomaciZadatak();
        zadatak.setOpis(request.getOpis());
        zadatak.setRok(request.getRok());
        zadatak.setPredmetId(request.getPredmetId());

        return zadatakRepository.save(zadatak);
    }


    public Predaja predaj(Integer studentId, Integer zadatakId) {
        DomaciZadatak zadatak = getById(zadatakId);

        usersServiceClient.getStudentById(studentId);

        boolean upisan = Boolean.TRUE.equals(
                usersServiceClient.jeUpisan(studentId, zadatak.getPredmetId()));
        if (!upisan) {
            throw new BusinessRuleViolationException(
                    "Student " + studentId + " nije upisan na predmet " + zadatak.getPredmetId());
        }

        if (LocalDate.now().isAfter(zadatak.getRok())) {
            throw new BusinessRuleViolationException(
                    "Rok za predaju zadatka (" + zadatak.getRok() + ") je istekao");
        }

        PredajaId id = new PredajaId(studentId, zadatakId);
        if (predajaRepository.existsById(id)) {
            throw new BusinessRuleViolationException(
                    "Student " + studentId + " je vec predao ovaj zadatak");
        }

        Predaja predaja = new Predaja(studentId, zadatak);
        return predajaRepository.save(predaja);
    }

    public Predaja oceniPredaju(Integer studentId, Integer zadatakId, Integer bodovi) {
        PredajaId id = new PredajaId(studentId, zadatakId);
        Predaja predaja = predajaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student " + studentId + " nije predao zadatak " + zadatakId));

        predaja.setBodovi(bodovi);
        return predajaRepository.save(predaja);
    }
}
