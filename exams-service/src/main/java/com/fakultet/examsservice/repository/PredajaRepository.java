package com.fakultet.examsservice.repository;

import com.fakultet.examsservice.model.Predaja;
import com.fakultet.examsservice.model.PredajaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredajaRepository extends JpaRepository<Predaja, PredajaId> {
    List<Predaja> findById_StudentId(Integer studentId);
    List<Predaja> findById_ZadatakId(Integer zadatakId);
}
