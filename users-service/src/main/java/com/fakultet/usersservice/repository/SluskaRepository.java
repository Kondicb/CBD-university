package com.fakultet.usersservice.repository;

import com.fakultet.usersservice.model.Sluska;
import com.fakultet.usersservice.model.SluskaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SluskaRepository extends JpaRepository<Sluska, SluskaId> {
    List<Sluska> findByStudent_StudentId(Integer studentId);
    boolean existsById(SluskaId id);
    long countById_PredmetId(Integer predmetId);
}
