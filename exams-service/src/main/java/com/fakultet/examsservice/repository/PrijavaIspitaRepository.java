package com.fakultet.examsservice.repository;

import com.fakultet.examsservice.model.PrijavaIspita;
import com.fakultet.examsservice.model.PrijavaIspitaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, PrijavaIspitaId> {
    List<PrijavaIspita> findById_StudentId(Integer studentId);
}
