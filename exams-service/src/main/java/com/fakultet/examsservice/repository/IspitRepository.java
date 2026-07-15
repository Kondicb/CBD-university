package com.fakultet.examsservice.repository;

import com.fakultet.examsservice.model.Ispit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IspitRepository extends JpaRepository<Ispit, Integer> {
    List<Ispit> findByPredmetId(Integer predmetId);
}
