package com.fakultet.usersservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sluska")
@Getter
@Setter
@NoArgsConstructor
public class Sluska {

    @EmbeddedId
    private SluskaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "datum_upisa", nullable = false)
    private LocalDate datumUpisa = LocalDate.now();

    public Sluska(Student student, Integer predmetId) {
        this.student = student;
        this.id = new SluskaId(student.getStudentId(), predmetId);
    }
}
