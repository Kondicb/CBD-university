package com.fakultet.academicservice.repository;

import com.fakultet.academicservice.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredmetRepository extends JpaRepository<Predmet, Integer> {
    List<Predmet> findBySmer_SmerId(Integer smerId);
    List<Predmet> findBySmer_SmerIdAndGodina(Integer smerId, Integer godina);
}
