package com.fakultet.usersservice.repository;

import com.fakultet.usersservice.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    List<Profesor> findByDepartmanId(Integer departmanId);
}
