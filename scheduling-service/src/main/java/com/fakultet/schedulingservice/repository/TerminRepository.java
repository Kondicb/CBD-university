package com.fakultet.schedulingservice.repository;

import com.fakultet.schedulingservice.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Integer> {
    List<Termin> findByUcionica_UcionicaIdAndDanUNedelji(Integer ucionicaId, String danUNedelji);
}
