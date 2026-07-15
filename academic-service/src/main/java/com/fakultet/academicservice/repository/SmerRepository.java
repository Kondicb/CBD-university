package com.fakultet.academicservice.repository;

import com.fakultet.academicservice.model.Smer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmerRepository extends JpaRepository<Smer, Integer> {
    List<Smer> findByDepartman_DepartmanId(Integer departmanId);
}
