package com.fakultet.schedulingservice.repository;

import com.fakultet.schedulingservice.model.IzvodjenjeNastave;
import com.fakultet.schedulingservice.model.IzvodjenjeNastaveId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IzvodjenjeNastaveRepository extends JpaRepository<IzvodjenjeNastave, IzvodjenjeNastaveId> {
    List<IzvodjenjeNastave> findById_SemestarId(Integer semestarId);
    List<IzvodjenjeNastave> findById_ProfesorIdAndId_SemestarId(Integer profesorId, Integer semestarId);
}
