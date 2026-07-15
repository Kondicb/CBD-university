package com.fakultet.academicservice.repository;

import com.fakultet.academicservice.model.Materijal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterijalRepository extends JpaRepository<Materijal, Integer> {
    List<Materijal> findByPredmet_PredmetId(Integer predmetId);
}
