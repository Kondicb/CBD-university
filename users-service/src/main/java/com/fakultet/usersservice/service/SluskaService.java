package com.fakultet.usersservice.service;

import com.fakultet.usersservice.client.AcademicServiceClient;
import com.fakultet.usersservice.dto.PredmetInfo;
import com.fakultet.usersservice.dto.UpisaniPredmetResponse;
import com.fakultet.usersservice.exception.BusinessRuleViolationException;
import com.fakultet.usersservice.exception.ResourceNotFoundException;
import com.fakultet.usersservice.model.Sluska;
import com.fakultet.usersservice.model.SluskaId;
import com.fakultet.usersservice.model.Student;
import com.fakultet.usersservice.repository.SluskaRepository;
import com.fakultet.usersservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SluskaService {

    private final SluskaRepository sluskaRepository;
    private final StudentRepository studentRepository;
    private final AcademicServiceClient academicServiceClient;

    public SluskaService(SluskaRepository sluskaRepository,
                          StudentRepository studentRepository,
                          AcademicServiceClient academicServiceClient) {
        this.sluskaRepository = sluskaRepository;
        this.studentRepository = studentRepository;
        this.academicServiceClient = academicServiceClient;
    }

    public Sluska upisiStudentaNaPredmet(Integer studentId, Integer predmetId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student sa id=" + studentId + " ne postoji"));

        PredmetInfo predmet = academicServiceClient.getPredmetById(predmetId);

        SluskaId id = new SluskaId(studentId, predmetId);
        if (sluskaRepository.existsById(id)) {
            throw new BusinessRuleViolationException(String.format(
                    "Student %d je vec upisan na predmet '%s' (id=%d)",
                    studentId, predmet.getNaziv(), predmetId));
        }

        Sluska sluska = new Sluska(student, predmetId);
        return sluskaRepository.save(sluska);
    }

    public long getBrojUpisanihStudenata(Integer predmetId) {
        return sluskaRepository.countById_PredmetId(predmetId);
    }

    public boolean jeUpisan(Integer studentId, Integer predmetId) {
        return sluskaRepository.existsById(new SluskaId(studentId, predmetId));
    }

    public void odjaviStudenta(Integer studentId, Integer predmetId) {
        SluskaId id = new SluskaId(studentId, predmetId);
        if (!sluskaRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Student " + studentId + " nije upisan na predmet " + predmetId);
        }
        sluskaRepository.deleteById(id);
    }

    public List<UpisaniPredmetResponse> getUpisaniPredmeti(Integer studentId) {
        List<Sluska> upisi = sluskaRepository.findByStudent_StudentId(studentId);

        return upisi.stream()
                .map(sluska -> {
                    Integer predmetId = sluska.getId().getPredmetId();
                    PredmetInfo predmet = academicServiceClient.getPredmetById(predmetId);
                    return new UpisaniPredmetResponse(
                            predmetId,
                            predmet.getNaziv(),
                            predmet.getEspb(),
                            sluska.getDatumUpisa().toString());
                })
                .collect(Collectors.toList());
    }
}
