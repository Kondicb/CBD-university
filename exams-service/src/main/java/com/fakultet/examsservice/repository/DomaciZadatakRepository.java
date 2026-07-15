package com.fakultet.examsservice.repository;

import com.fakultet.examsservice.model.DomaciZadatak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomaciZadatakRepository extends JpaRepository<DomaciZadatak, Integer> {
    List<DomaciZadatak> findByPredmetId(Integer predmetId);
}
