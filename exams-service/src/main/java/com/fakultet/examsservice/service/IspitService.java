package com.fakultet.examsservice.service;

import com.fakultet.examsservice.client.AcademicServiceClient;
import com.fakultet.examsservice.client.SchedulingServiceClient;
import com.fakultet.examsservice.client.UsersServiceClient;
import com.fakultet.examsservice.dto.IspitRequest;
import com.fakultet.examsservice.exception.BusinessRuleViolationException;
import com.fakultet.examsservice.exception.ResourceNotFoundException;
import com.fakultet.examsservice.model.Ispit;
import com.fakultet.examsservice.model.PrijavaIspita;
import com.fakultet.examsservice.model.PrijavaIspitaId;
import com.fakultet.examsservice.repository.IspitRepository;
import com.fakultet.examsservice.repository.PrijavaIspitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IspitService {

    private final IspitRepository ispitRepository;
    private final PrijavaIspitaRepository prijavaIspitaRepository;
    private final AcademicServiceClient academicServiceClient;
    private final SchedulingServiceClient schedulingServiceClient;
    private final UsersServiceClient usersServiceClient;

    public IspitService(IspitRepository ispitRepository,
                         PrijavaIspitaRepository prijavaIspitaRepository,
                         AcademicServiceClient academicServiceClient,
                         SchedulingServiceClient schedulingServiceClient,
                         UsersServiceClient usersServiceClient) {
        this.ispitRepository = ispitRepository;
        this.prijavaIspitaRepository = prijavaIspitaRepository;
        this.academicServiceClient = academicServiceClient;
        this.schedulingServiceClient = schedulingServiceClient;
        this.usersServiceClient = usersServiceClient;
    }

    public List<Ispit> getAll() {
        return ispitRepository.findAll();
    }

    public Ispit getById(Integer id) {
        return ispitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ispit sa id=" + id + " ne postoji"));
    }

    public Ispit create(IspitRequest request) {
        academicServiceClient.getPredmetById(request.getPredmetId());
        schedulingServiceClient.getTerminById(request.getTerminId());

        Ispit ispit = new Ispit();
        ispit.setDatum(request.getDatum());
        ispit.setRok(request.getRok());
        ispit.setPredmetId(request.getPredmetId());
        ispit.setTerminId(request.getTerminId());

        return ispitRepository.save(ispit);
    }
    public PrijavaIspita prijaviNaIspit(Integer studentId, Integer ispitId) {
        Ispit ispit = getById(ispitId);

        usersServiceClient.getStudentById(studentId);

        boolean upisan = Boolean.TRUE.equals(
                usersServiceClient.jeUpisan(studentId, ispit.getPredmetId()));
        if (!upisan) {
            throw new BusinessRuleViolationException(
                    "Student " + studentId + " nije upisan na predmet " + ispit.getPredmetId()
                            + " i ne moze se prijaviti na ovaj ispit");
        }

        if (java.time.LocalDate.now().isAfter(ispit.getDatum())) {
            throw new BusinessRuleViolationException("Rok za prijavu na ovaj ispit je istekao");
        }

        PrijavaIspitaId id = new PrijavaIspitaId(studentId, ispitId);
        if (prijavaIspitaRepository.existsById(id)) {
            throw new BusinessRuleViolationException(
                    "Student " + studentId + " je vec prijavljen na ispit " + ispitId);
        }

        PrijavaIspita prijava = new PrijavaIspita(studentId, ispit);
        return prijavaIspitaRepository.save(prijava);
    }

    public PrijavaIspita unesiOcenu(Integer studentId, Integer ispitId, Integer ocena) {
        PrijavaIspitaId id = new PrijavaIspitaId(studentId, ispitId);
        PrijavaIspita prijava = prijavaIspitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student " + studentId + " nije prijavljen na ispit " + ispitId));

        prijava.setOcena(ocena);
        return prijavaIspitaRepository.save(prijava);
    }
}
