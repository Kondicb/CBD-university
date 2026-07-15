package com.fakultet.schedulingservice.repository;

import com.fakultet.schedulingservice.model.Semestar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestarRepository extends JpaRepository<Semestar, Integer> {
}
